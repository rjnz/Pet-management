package ru.rjnz.petmanagement.service;

import org.springframework.stereotype.Service;
import ru.rjnz.petmanagement.model.Pet;
import ru.rjnz.petmanagement.repository.PetRepository;
import ru.rjnz.petmanagement.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetService(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public List<Pet> getAll(int userId) {
        return petRepository.getAll(userId);
    }

    public Pet getById(int id, int userId) {
        return petRepository.findById(id)
                .filter(pet -> pet.getUser().id() == userId)
                .orElse(null);
    }

    @Transactional
    public Pet save(Pet pet, int userId) {
        if (!pet.isNew() && getById(pet.id(), userId) == null) {
            return null;
        }
        pet.setUser(userRepository.getById(userId));
        return petRepository.save(pet);
    }

    public void update(Pet pet, int id, int userId) {
        petRepository.save(pet);
    }

    public void delete(int id, int userId) {
        petRepository.deleteById(id, userId);
    }
}
