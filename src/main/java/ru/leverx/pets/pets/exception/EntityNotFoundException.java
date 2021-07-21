package ru.leverx.pets.pets.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Object entity, long id) {
        super(entity + " with id=" + id + " doesn't exist.");
    }
}