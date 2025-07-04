package org.example.customexceptions;

public class NotAbleToDeleteException extends RuntimeException {
    public NotAbleToDeleteException(String message) {
        super(message);
    }
}
