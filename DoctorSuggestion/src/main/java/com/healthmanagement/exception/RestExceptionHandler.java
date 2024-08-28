package com.healthmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoSuchDoctorException.class)
    public ResponseEntity<ErrorMessage> noSuchDoctorException(NoSuchDoctorException exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMsg(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchPatientException.class)
    public ResponseEntity<ErrorMessage> noSuchPatientException(NoSuchPatientException exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMsg(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchSpecialityException.class)
    public ResponseEntity<ErrorMessage> noSuchSpecialityException(NoSuchSpecialityException exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMsg(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoSuchSymptomException.class)
    public ResponseEntity<ErrorMessage> noSuchSymptomException(NoSuchSymptomException exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMsg(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorUnavailableException.class)
    public ResponseEntity<ErrorMessage> doctorUnavailableException(DoctorUnavailableException exception){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setErrorMsg(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
