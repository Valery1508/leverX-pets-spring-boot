package ru.leverx.pets.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static ru.leverx.pets.pets.exception.ExceptionMessages.NULL_FIELD_MESSAGE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapPetsDto {

    @NotNull(message = NULL_FIELD_MESSAGE)
    private long firstPersonId;

    @NotNull(message = NULL_FIELD_MESSAGE)
    private long secondPersonId;

    @NotNull(message = NULL_FIELD_MESSAGE)
    private long firstPetId;

    @NotNull(message = NULL_FIELD_MESSAGE)
    private long secondPetId;
}
