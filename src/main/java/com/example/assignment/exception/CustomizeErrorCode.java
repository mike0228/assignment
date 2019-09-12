package com.example.assignment.exception;


public enum CustomizeErrorCode implements InterfaceCustomizeErrorCode{
    POST_NOT_FOUND(2001,"帖子不存在或已被删除"),
    TARGET_PARAM_NOT_FOUND(2002,"没有回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器炸了，稍等一会再来吧"),
    TYPE_PARAM_WRONG(2005,"回复类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    CONTENT_IS_EMPTY(2007,"回复不能为空"),
    READ_NOTIFICATION_FAIL(2008,"非法读取"),
    NOTIFICATION_NOT_FOUND(2009,"消息不存在或已被删除"),
    UNABLE_TO_DELETE(2010,"非法删除操作")
    ;

    @Override
    public String getMessage(){
        return  message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
