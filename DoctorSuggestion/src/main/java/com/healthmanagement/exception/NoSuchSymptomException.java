package com.healthmanagement.exception;

public class NoSuchSymptomException extends RuntimeException {

    public NoSuchSymptomException(){
        super();
    }
    public NoSuchSymptomException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
