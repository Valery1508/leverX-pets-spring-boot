package ru.leverx.pets.pets.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leverx.pets.pets.dto.PetDto;
import ru.leverx.pets.pets.entity.Person;
import ru.leverx.pets.pets.entity.Pet;
import ru.leverx.pets.pets.exception.EntityNotFoundException;
import ru.leverx.pets.pets.exception.OwnershipException;
import ru.leverx.pets.pets.mapper.PetMapper;
import ru.leverx.pets.pets.repository.PetRepository;
import ru.leverx.pets.pets.service.PersonService;
import ru.leverx.pets.pets.service.PetService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final PersonService personService;

    @Override
    public List<PetDto> getPets() {
        return toDtos(petRepository.findAll());
    }

    @Override
    public PetDto getPetById(long id) {
        return petRepository.findById(id)
                .map(petMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Pet.class.getName(), id));
    }

    @Override
    public PetDto createPet(PetDto petDto) {
        long personId = petDto.getPersonId();

        if (!personService.checkPersonExistence(personId)) {
            throw new EntityNotFoundException(Person.class.getName(), personId);
        }

        Pet pet = petMapper.toEntity(petDto);
        Pet savedPet = petRepository.save(pet);
        return getPetById(savedPet.getId());
    }

    @Override
    public void deletePetById(long id) {
        if (checkPetExistence(id)) {
            petRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Pet.class.getName(), id);
        }
    }

    @Override
    public PetDto updatePet(long id, PetDto petDto) {
        if (!checkPetExistence(id)) {
            throw new EntityNotFoundException(Pet.class.getName(), id);
        } else {
            long personId = petDto.getPersonId();
            if (!personService.checkPersonExistence(personId)) {
                throw new EntityNotFoundException(Person.class.getName(), personId);
            }
        }

        petDto.setId(id);
        Pet pet = petMapper.toEntity(petDto);

        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    @Override
    public boolean checkPetExistence(long id) {
        return petRepository.existsById(id);
    }

    @Override
    public void updatePersonId(long personId, long petId) {
        petRepository.updatePersonId(personId, petId);
    }

    @Override
    public void checkOwnership(Long personId, Long petId) {
        Optional<Person> person = petRepository.findPersonByPetId(petId);
        if (!personId.equals(person.get().getId())) {
            throw new OwnershipException(personId, petId);
        }
    }

    private List<PetDto> toDtos(List<Pet> pets) {
        return pets.stream()
                .map(petMapper::toDto)
                .collect(toList());
    }
}