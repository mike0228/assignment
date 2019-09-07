package com.example.assignment.exception;


public enum CustomizeErrorCode implements InterfaceCustomizeErrorCode{
    POST_NOT_FOUND("帖子不存在或已被删除");
    private String message;
    @Override
    public String getMessage(){
        return  message;
    }
    CustomizeErrorCode(String message){
        this.message=message;
    }
}
