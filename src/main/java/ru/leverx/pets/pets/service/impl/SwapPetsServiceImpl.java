package ru.leverx.pets.pets.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.pets.pets.dto.SwapPetsDto;
import ru.leverx.pets.pets.entity.Person;
import ru.leverx.pets.pets.exception.OwnershipException;
import ru.leverx.pets.pets.repository.PetRepository;
import ru.leverx.pets.pets.service.PersonService;
import ru.leverx.pets.pets.service.PetService;
import ru.leverx.pets.pets.service.SwapPetsService;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SwapPetsServiceImpl implements SwapPetsService {

    private final PetRepository petRepository;

    private final PersonService personService;
    private final PetService petService;

    @Override
    public void swapPets(SwapPetsDto swapPetsDto) {
        long firstPersonId = swapPetsDto.getFirstPersonId();
        long firstPetId = swapPetsDto.getFirstPetId();

        if (checkExistenceAndOwnership(firstPersonId, firstPetId)) {
            long secondPersonId = swapPetsDto.getSecondPersonId();
            long secondPetId = swapPetsDto.getSecondPetId();

            if (checkExistenceAndOwnership(secondPersonId, secondPetId)) {

                petRepository.updatePersonId(firstPersonId, secondPetId);
                petRepository.updatePersonId(secondPersonId, firstPetId);

            } else {
                throw new OwnershipException(secondPersonId, secondPetId);
            }
        } else {
            throw new OwnershipException(firstPersonId, firstPetId);
        }
    }

    private boolean checkExistenceAndOwnership(Long personId, Long petId) {
        return personService.checkPersonExistence(personId)
                && petService.checkPetExistence(petId)
                && checkOwnerShip(personId, petId);
    }

    private boolean checkOwnerShip(Long personId, Long petId) {
        Person person = petRepository.findPersonByPetId(petId);
        return personId.equals(person.getId());
    }
}
