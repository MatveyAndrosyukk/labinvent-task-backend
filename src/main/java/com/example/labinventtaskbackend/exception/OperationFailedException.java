package com.example.labinventtaskbackend.exception;

public class OperationFailedException extends Exception{

    private final static long serialVersionUID = 1L;

    public OperationFailedException(String message) {
        super(message);
    }
}
