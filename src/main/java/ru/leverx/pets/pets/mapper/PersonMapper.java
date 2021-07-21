package ru.leverx.pets.pets.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leverx.pets.pets.dto.PersonRequestDto;
import ru.leverx.pets.pets.dto.PersonResponseDto;
import ru.leverx.pets.pets.entity.Person;

@Component
@AllArgsConstructor
public class PersonMapper {

    private final PetMapper petMapper;

    public Person toEntity(PersonRequestDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        return person;
    }

    public PersonResponseDto toDto(Person person) {
        PersonResponseDto personDto = new PersonResponseDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setPets(petMapper.toDtos(person.getPets()));
        return personDto;
    }
}