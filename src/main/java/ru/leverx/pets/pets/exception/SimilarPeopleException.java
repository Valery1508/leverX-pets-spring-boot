package ru.leverx.pets.pets.exception;

public class SimilarPeopleException extends RuntimeException {

    public SimilarPeopleException(long id) {
        super("It is 2 similar people with id=" + id);
    }
}