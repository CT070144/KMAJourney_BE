package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    APIResponse<AuthenticationResponse> AuthenticationUser(@RequestBody AuthenticationRequest request){

        return APIResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticated(request))
                .build();
    }
    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> IntrospectToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return APIResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspectToken(request))
                .build();
    }
}
