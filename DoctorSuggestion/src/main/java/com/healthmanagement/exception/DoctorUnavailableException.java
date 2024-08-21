package com.healthmanagement.exception;

public class DoctorUnavailableException extends RuntimeException {

    public DoctorUnavailableException(){
        super();
    }
    public DoctorUnavailableException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
