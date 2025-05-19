package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.Data;

import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationException extends RuntimeException{
    ErrorCode errorCode;
}
