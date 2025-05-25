package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.request.LogoutRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    @PostMapping("/logout")
    APIResponse<Void> logout(HttpServletRequest request) throws ParseException, JOSEException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken =  bearerToken.substring(7);
        }
        LogoutRequest logoutRequest = new LogoutRequest(bearerToken);
        authenticationService.logout(logoutRequest);
        return APIResponse.<Void>builder().build();
    }
}
