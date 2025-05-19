package com.example.demo.dto.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String fullName;
    @Past()
    LocalDate  dateOfBirth;
    String email;
    String phone;
    String gender;
    @Size(min=5,message = "USERNAME_INVALID")
    String userName;
    @Size(min=3,message = "PASSWORD_INVALID")
    String passWord;
}
