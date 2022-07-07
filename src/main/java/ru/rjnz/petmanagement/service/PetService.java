package ru.rjnz.petmanagement.service;

import org.springframework.stereotype.Service;
import ru.rjnz.petmanagement.model.Pet;
import ru.rjnz.petmanagement.repository.PetRepository;

import java.util.List;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> getAll() {
        return repository.findAll();
    }
}
