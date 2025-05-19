package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping()
    APIResponse<AuthenticationResponse> AuthenticationUser(@RequestBody AuthenticationRequest request){

        return APIResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder().authenticated(authenticationService.authenticated(request)).build())
                .build();
    }
}
