package ru.leverx.pets.pets.mapper;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leverx.pets.pets.dto.PetDto;
import ru.leverx.pets.pets.entity.Pet;
import ru.leverx.pets.pets.entity.PetType;
import ru.leverx.pets.pets.repository.PersonRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class PetMapper {

    private final PersonRepository personRepository;

    public Pet toEntity(PetDto petDto) {
        Pet pet = new Pet();
        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        pet.setType(PetType.valueOf(petDto.getType()));
        pet.setPerson(personRepository.findById(petDto.getPersonId()).get());
        return pet;
    }

    public PetDto toDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        petDto.setType(String.valueOf(pet.getType()));
        petDto.setPersonId(pet.getPerson().getId());
        return petDto;
    }

    public List<PetDto> toDtos(List<Pet> pets) {
        return pets.stream().map(this::toDto).collect(toList());
    }

    public List<Pet> toEntities(List<PetDto> petsDto) {
        return petsDto.stream().map(this::toEntity).collect(toList());
    }
}
