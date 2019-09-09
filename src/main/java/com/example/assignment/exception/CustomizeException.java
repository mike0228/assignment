package com.example.assignment.exception;

public class CustomizeException extends RuntimeException{
    private  String message;
    private Integer code;
    public CustomizeException(InterfaceCustomizeErrorCode errorcode){
        this.code=errorcode.getCode();
        this.message=errorcode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
    public Integer getCode(){
        return code;
    }
}
