package org.example.customexceptions;

public class NotfoundException extends RuntimeException {
    public NotfoundException(String message) {
        super(message);
    }
}
