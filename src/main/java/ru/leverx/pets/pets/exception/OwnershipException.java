package ru.leverx.pets.pets.exception;

public class OwnershipException extends RuntimeException {

    public OwnershipException(long personId, long petId) {
        super("Person with id=" + personId + " is NOT an owner of Pet with id=" + petId);
    }
}