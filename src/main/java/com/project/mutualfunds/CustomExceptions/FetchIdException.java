package com.project.mutualfunds.CustomExceptions;

public class FetchIdException extends RuntimeException {
    public FetchIdException(String message) {
        super(message);
    }
}
