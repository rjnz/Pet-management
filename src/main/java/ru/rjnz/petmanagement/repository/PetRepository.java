package ru.rjnz.petmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rjnz.petmanagement.model.Pet;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query("SELECT p FROM Pet p WHERE p.user.id=:userId")
    List<Pet> getAll(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.user.id=:userId AND p.id=:id")
    void deleteById(int id, int userId);
}
