package ru.rjnz.petmanagement.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rjnz.petmanagement.model.Pet;

import java.util.List;

@Repository
public class DataJpaPetRepository {

    private final CrudPetRepository crudPetRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaPetRepository(CrudPetRepository crudPetRepository, CrudUserRepository crudUserRepository) {
        this.crudPetRepository = crudPetRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    public Pet save(Pet pet, int userId) {
        if (!pet.isNew() && get(pet.id(), userId) == null) {
            return null;
        }
        pet.setUser(crudUserRepository.getById(userId));
        return crudPetRepository.save(pet);
    }

    public boolean delete(int id, int userId) {
        return crudPetRepository.delete(id, userId) != 0;
    }

    public Pet get(int id, int userId) {
        return crudPetRepository.findById(id)
                .filter(p -> p.getUser().id() == userId)
                .orElse(null);
    }

    public List<Pet> getAll(int userId) {
        return crudPetRepository.getAll(userId);
    }
}
