package com.example.assignment.exception;

public class CustomizeException extends RuntimeException{
    private  String message;
    public CustomizeException(String message){
        this.message=message;
    }
    public CustomizeException(InterfaceCustomizeErrorCode errorcode){
        this.message=errorcode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
