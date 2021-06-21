package ru.leverx.pets.pets.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.pets.pets.dto.SwapPetsDto;
import ru.leverx.pets.pets.exception.SimilarPeopleException;
import ru.leverx.pets.pets.service.PersonService;
import ru.leverx.pets.pets.service.PetService;
import ru.leverx.pets.pets.service.SwapPetsService;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SwapPetsServiceImpl implements SwapPetsService {

    private final PersonService personService;
    private final PetService petService;

    @Override
    public void swapPets(SwapPetsDto swapPetsDto) {

        long firstPersonId = swapPetsDto.getFirstPersonId();
        long firstPetId = swapPetsDto.getFirstPetId();
        long secondPersonId = swapPetsDto.getSecondPersonId();
        long secondPetId = swapPetsDto.getSecondPetId();

        checkPersonAndPetExistence(firstPersonId, firstPetId);
        checkPersonAndPetExistence(secondPersonId, secondPetId);

        checkSimilarPersonId(firstPersonId, secondPersonId);

        petService.checkOwnership(firstPersonId, firstPetId);
        petService.checkOwnership(secondPersonId, secondPetId);

        petService.updatePersonId(firstPersonId, secondPetId);
        petService.updatePersonId(secondPersonId, firstPetId);
    }

    private void checkPersonAndPetExistence(Long personId, Long petId) {
        personService.checkPersonExistence(personId);
        petService.checkPetExistence(petId);
    }

    private void checkSimilarPersonId(Long firstPersonId, Long secondPersonId) {
        if (firstPersonId.equals(secondPersonId)) {
            throw new SimilarPeopleException(firstPersonId);
        }
    }
}
