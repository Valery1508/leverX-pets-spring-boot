package ru.leverx.pets.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leverx.pets.pets.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
