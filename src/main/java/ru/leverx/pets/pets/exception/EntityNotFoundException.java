package ru.leverx.pets.pets.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1515502198795323189L;

    public EntityNotFoundException(Object entity, long id) {
        super(entity + " with id=" + id + " doesn't exist.");
    }

}