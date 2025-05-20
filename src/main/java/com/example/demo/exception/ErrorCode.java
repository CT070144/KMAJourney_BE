package com.example.demo.exception;




public enum ErrorCode {
    USER_EXISTED(1001,"User existed"),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error"),
    USERNAME_INVALID(1002,"Username must be at least 5 characters"),
    PASSWORD_INVALID(1003,"Password must be at least 3 characters"),
    USER_NOT_EXIST(1004,"User not exit"),
    KEY_INVALID(1005,"KEY INVALID"),
    EMAIL_EXISTED(1006,"Email existed"),
    UNAUTHENTICATED(1007,"unauthenticated");

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
