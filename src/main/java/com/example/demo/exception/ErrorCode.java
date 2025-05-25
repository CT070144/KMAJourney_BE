package com.example.demo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1002,"Username must be at least 5 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Password must be at least 3 characters",HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1004,"User not exit",HttpStatus.NOT_FOUND),
    KEY_INVALID(1005,"KEY INVALID",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1006,"Email existed",HttpStatus.BAD_REQUEST),
    EXISTED(10061,"Existed",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    ACCESSDENIED(1008,"You don not have permission",HttpStatus.FORBIDDEN);
    int code;
    String message;
    HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }


}
