package ru.rjnz.petmanagement.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.rjnz.petmanagement.model.Pet;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudPetRepository extends JpaRepository<Pet, Integer> {
    @Query("SELECT p FROM Pet p WHERE p.user.id=:userId")
    List<Pet> getAll(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.user.id=:userId AND p.id=:id")
    int delete(int id, int userId);
}
