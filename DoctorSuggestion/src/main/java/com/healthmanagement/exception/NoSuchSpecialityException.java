package com.healthmanagement.exception;

public class NoSuchSpecialityException extends RuntimeException {

    public NoSuchSpecialityException(){
        super();
    }
    public NoSuchSpecialityException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
