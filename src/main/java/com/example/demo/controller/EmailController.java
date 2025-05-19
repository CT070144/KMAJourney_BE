package com.example.demo.controller;

import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.EmailResponse;
import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/send-otp")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmailController {
    EmailService emailService;

   @PostMapping
   APIResponse<EmailResponse> sendEmail(@RequestBody EmailRequest request) throws MessagingException {


      return APIResponse.<EmailResponse>builder()
              .result(emailService.sendOTP(request.getEmail()))
              .build();
   }



}
