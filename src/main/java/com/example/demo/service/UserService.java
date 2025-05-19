package com.example.demo.service;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.request.UpdatePasswordRequest;
import com.example.demo.dto.response.CheckEmailResponse;
import com.example.demo.dto.response.UpdatePasswordResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;



    public UserResponse createUser(UserCreateRequest request){

        User user = userMapper.toUser(request);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        if(userRepository.existsByuserName(user.getUserName())){
            throw new ApplicationException(ErrorCode.USER_EXISTED);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse getUser(String userId){

        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXIST)));
    }
    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user = userMapper.toUser(getUser(id));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getNewPassword()));
        try {
            User res = userRepository.save(user);
            return UpdatePasswordResponse.builder()
                    .status("success")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return UpdatePasswordResponse.builder()
                    .status("failed")
                    .build();
        }



    }
    public String deleteUser(String id){
        this.getUser(id);
        userRepository.deleteById(id);
        return "Deleted";
    }
    public CheckEmailResponse checkEmail(EmailRequest request){
        return CheckEmailResponse.builder()
                .existed(userRepository.existsByEmail(request.getEmail()))
                .build();
    }

}
