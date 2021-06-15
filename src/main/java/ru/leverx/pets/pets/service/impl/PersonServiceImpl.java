package ru.leverx.pets.pets.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.pets.pets.dto.PersonRequestDto;
import ru.leverx.pets.pets.dto.PersonResponseDto;
import ru.leverx.pets.pets.entity.Person;
import ru.leverx.pets.pets.exception.EntityNotFoundException;
import ru.leverx.pets.pets.mapper.PersonMapper;
import ru.leverx.pets.pets.repository.PersonRepository;
import ru.leverx.pets.pets.service.PersonService;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponseDto getPersonById(long id) {
        return personRepository.findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Person.class.getName(), id));
    }

    @Override
    public List<PersonResponseDto> getPeople() {
        return toDtos(personRepository.findAll());
    }

    @Override
    public PersonResponseDto createPerson(PersonRequestDto personRequestDto) {
        Person person = personMapper.toEntity(personRequestDto);
        Person savedPerson = personRepository.save(person);
        return getPersonById(savedPerson.getId());
    }

    @Override
    public void deletePersonById(long id) {
        if (checkPersonExistence(id)) {
            personRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Person.class.getName(), id);
        }
    }

    @Override
    public PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto) {
        if (!checkPersonExistence(id)) {
            throw new EntityNotFoundException(Person.class.getName(), id);
        }

        personRequestDto.setId(id);
        Person person = personMapper.toEntity(personRequestDto);

        Person savedPerson = personRepository.save(person);
        return personMapper.toDto(savedPerson);
    }

    @Override
    public boolean checkPersonExistence(long id) {
        return personRepository.existsById(id);
    }

    private List<PersonResponseDto> toDtos(List<Person> persons) {
        return persons.stream()
                .map(personMapper::toDto)
                .collect(toList());
    }
}