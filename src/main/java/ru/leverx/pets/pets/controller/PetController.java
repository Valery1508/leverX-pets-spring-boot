package ru.leverx.pets.pets.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leverx.pets.pets.dto.PetDto;
import ru.leverx.pets.pets.dto.SwapPetsDto;
import ru.leverx.pets.pets.service.PetService;
import ru.leverx.pets.pets.service.SwapPetsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;
    private final SwapPetsService swapPetsService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @GetMapping
    public List<PetDto> getPets() {
        return petService.getPets();
    }

    @PostMapping
    public ResponseEntity<PetDto> createPerson(@Valid @RequestBody PetDto petDto) {
        return ResponseEntity.ok(petService.createPet(petDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.ok("Pet with id=" + id + " was successfully deleted");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetDto> updatePetById(@PathVariable Long id,
                                                @Valid @RequestBody PetDto petDto) {
        return ResponseEntity.ok(petService.updatePet(id, petDto));
    }

    @PostMapping(value = "/swap")
    public ResponseEntity<String> swapPets(@Valid @RequestBody SwapPetsDto swapPetsDto) {
        swapPetsService.swapPets(swapPetsDto);
        return ResponseEntity.ok("Pets were successfully swapped :)");
    }
}
