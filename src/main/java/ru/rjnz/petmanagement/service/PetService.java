package ru.rjnz.petmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.rjnz.petmanagement.model.Pet;
import ru.rjnz.petmanagement.repository.DataJpaPetRepository;

import java.util.List;

import static ru.rjnz.petmanagement.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class PetService {
    private final DataJpaPetRepository repository;

    public PetService(DataJpaPetRepository repository) {
        this.repository = repository;
    }

    public Pet get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Pet> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Pet pet, int userId) {
        Assert.notNull(pet, "pet must not be null");
        checkNotFoundWithId(repository.save(pet, userId), pet.id());
    }

    public Pet create(Pet pet, int userId) {
        Assert.notNull(pet, "pet must not be null");
        return repository.save(pet, userId);
    }
}
