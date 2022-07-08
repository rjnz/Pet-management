package ru.rjnz.petmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rjnz.petmanagement.model.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query("SELECT p FROM Pet p WHERE p.user.id=:userId")
    List<Pet> getAll(int userId);
}
