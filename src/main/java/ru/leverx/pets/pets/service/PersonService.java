package ru.leverx.pets.pets.service;


import ru.leverx.pets.pets.dto.PersonRequestDto;
import ru.leverx.pets.pets.dto.PersonResponseDto;

import java.util.List;

public interface PersonService {
    PersonResponseDto getPersonById(long id);

    List<PersonResponseDto> getPeople();

    void deletePersonById(long id);

    PersonResponseDto createPerson(PersonRequestDto personRequestDto);

    PersonResponseDto updatePerson(long id, PersonRequestDto personRequestDto);

    boolean checkPersonExistence(long id);
}
