package com.healthmanagement.exception;

import lombok.Data;

@Data
public class ErrorMessage {

    private int errorCode;
    private String errorMsg;
}
