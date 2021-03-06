package ru.leverx.pets.pets.service;

import ru.leverx.pets.pets.dto.PetDto;

import java.util.List;

public interface PetService {

    PetDto getPetById(long id);

    List<PetDto> getPets();

    void deletePetById(long id);

    PetDto updatePet(long id, PetDto petDto);

    PetDto createPet(PetDto petDto);

    boolean checkPetExistence(long id);

    void updatePersonId(long personId, long petId);

    void checkOwnership(Long personId, Long petId);
}
