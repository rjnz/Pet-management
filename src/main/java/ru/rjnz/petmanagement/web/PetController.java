package ru.rjnz.petmanagement.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.rjnz.petmanagement.AuthorizedUser;
import ru.rjnz.petmanagement.View;
import ru.rjnz.petmanagement.model.Pet;
import ru.rjnz.petmanagement.service.PetService;

import java.net.URI;
import java.util.List;

import static ru.rjnz.petmanagement.util.validation.ValidationUtil.assureIdConsistent;
import static ru.rjnz.petmanagement.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = PetController.PETS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PetController {
    static final String PETS_URL = "/profile/pets";
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> getAll() {
        int userId = AuthorizedUser.user.id();
        return service.getAll(userId);
    }

    @GetMapping("/{id}")
    public Pet get(@PathVariable int id) {
        int userId = AuthorizedUser.user.id();
        return service.get(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> save(@Validated(View.Web.class) @RequestBody Pet pet) {
        int userId = AuthorizedUser.user.id();
        checkNew(pet);
        Pet created = service.create(pet, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PetController.PETS_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Pet pet, @PathVariable int id) {
        assureIdConsistent(pet, id);
        int userId = AuthorizedUser.user.id();
        service.update(pet, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        int userId = AuthorizedUser.user.id();
        service.delete(id, userId);
    }
}
