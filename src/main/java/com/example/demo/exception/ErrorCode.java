package com.example.demo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1002,"Username must be at least {min} characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Password must be at least {min} characters",HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1004,"User not exit",HttpStatus.NOT_FOUND),
    KEY_INVALID(1005,"KEY INVALID",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1006,"Email existed",HttpStatus.BAD_REQUEST),
    EXISTED(10061,"Existed",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    ACCESSDENIED(1008,"You don not have permission",HttpStatus.FORBIDDEN),
    DOB_INVALID(1009,"Age must be greater {min}",HttpStatus.BAD_REQUEST),
    STUDENTCODE_EXISTED(1010,"Student code existed",HttpStatus.BAD_REQUEST),
    FOREIGN_KEY(1011,"Cannot be deleted because it is referenced as a foreign key in another table.",HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }


}
