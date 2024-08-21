package com.healthmanagement.exception;

public class NoSuchsymptomException extends RuntimeException {

    public NoSuchsymptomException(){
        super();
    }
    public NoSuchsymptomException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
