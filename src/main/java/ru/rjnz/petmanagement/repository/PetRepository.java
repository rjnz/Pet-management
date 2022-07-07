package ru.rjnz.petmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rjnz.petmanagement.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
