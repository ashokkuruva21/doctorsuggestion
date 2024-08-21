package com.healthmanagement.exception;

public class NoSuchPatientException extends RuntimeException {
    public NoSuchPatientException(){
        super();
    }
    public NoSuchPatientException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
