package ru.leverx.pets.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leverx.pets.pets.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
