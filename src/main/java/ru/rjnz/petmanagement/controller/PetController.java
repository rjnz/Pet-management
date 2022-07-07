package ru.rjnz.petmanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rjnz.petmanagement.model.Pet;
import ru.rjnz.petmanagement.service.PetService;

import java.util.List;

@RestController
@RequestMapping(value = "/profile/pets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PetController {
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> getAll() {
        return service.getAll();
    }
}
