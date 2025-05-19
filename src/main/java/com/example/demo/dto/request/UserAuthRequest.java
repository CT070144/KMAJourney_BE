package com.example.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthRequest {

        @Size(min=5,message = "USERNAME_INVALID")
        String userName;
        @Size(min=3,message = "PASSWORD_INVALID")
        String passWord;
    }


