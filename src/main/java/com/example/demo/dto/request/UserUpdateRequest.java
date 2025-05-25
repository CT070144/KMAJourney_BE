package com.example.demo.dto.request;

import com.example.demo.customAnotation.DobConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String fullName;
    String passWord;
    @DobConstraint(min=18,message = "DOB_INVALID")
    LocalDate dob;
    List<String> roles;

}
