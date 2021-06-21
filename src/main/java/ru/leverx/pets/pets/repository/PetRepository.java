package ru.leverx.pets.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.leverx.pets.pets.entity.Person;
import ru.leverx.pets.pets.entity.Pet;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "SELECT p.person FROM Pet p where p.id = :petId")
    Optional<Person> findPersonByPetId(@Param("petId") Long petId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Pet p SET p.person.id = :personId WHERE p.id = :petId")
    void updatePersonId(@Param("personId") Long personId,
                        @Param("petId") Long petId);
}
