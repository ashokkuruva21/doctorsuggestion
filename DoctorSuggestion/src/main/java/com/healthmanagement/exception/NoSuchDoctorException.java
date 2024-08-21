package com.healthmanagement.exception;

public class NoSuchDoctorException extends RuntimeException {

    public NoSuchDoctorException(){
        super();
    }
    public NoSuchDoctorException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
