package com.example.demo.Mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermisstionResponse;
import com.example.demo.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermisstionResponse toPermissionResponse(Permission permission);
}
