package com.example.demo.Mapper;

import com.example.demo.dto.request.UpdatePasswordRequest;
import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);

    User toUser(UserResponse response);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
