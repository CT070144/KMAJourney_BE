package com.example.demo.controller;


import com.example.demo.dto.request.*;
import com.example.demo.dto.response.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;


    @PostMapping("/create")
    APIResponse<UserResponse> createUser(@RequestBody @Valid  UserCreateRequest request){
        System.out.println(request.toString());
        return APIResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PostMapping("/check-email")
    APIResponse<CheckEmailResponse> getEmailUsers(@RequestBody EmailRequest request){
        return APIResponse.<CheckEmailResponse>builder()
                .result(userService.checkEmail(request))
                .build();
    }
    @PostMapping("/update-password")
    APIResponse<UpdatePasswordResponse> updatePassword(@RequestBody UpdatePasswordRequest request){
        return APIResponse.<UpdatePasswordResponse>builder()
                .result(userService.updatePassword(request))
                .build();
    }
    @GetMapping()
    APIResponse<List<UserResponse>> getUsers(){
        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }
    @GetMapping("/{userID}")
    APIResponse<UserResponse> getUser(@PathVariable("userID") String userId){
      return APIResponse.<UserResponse>builder()
              .result(userService.getUser(userId))
              .build();
    }
    @PutMapping("/{userID}")
    APIResponse<UserResponse> updateUser(@PathVariable("userID") String id, @RequestBody @Valid UserUpdateRequest request){
        return APIResponse.<UserResponse>builder()
                .result(userService.updateUser(id,request))
                .build();
    }
    @DeleteMapping("/{userId}")
    APIResponse deleteUser(@PathVariable String userId){
        return APIResponse.builder()
                .result(userService.deleteUser(userId))
                .build();
    }


}
