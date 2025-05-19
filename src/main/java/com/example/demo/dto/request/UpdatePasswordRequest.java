package com.example.demo.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UpdatePasswordRequest {
    String email;
    String newPassword;
}
