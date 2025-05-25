package com.example.demo.service;

import com.example.demo.Mapper.PermissionMapper;
import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermisstionResponse;
import com.example.demo.entity.Permission;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    public PermisstionResponse create (PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        if(permissionRepository.existsByName(permission.getName())) throw new ApplicationException(ErrorCode.EXISTED);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }
    public List<PermisstionResponse> getAll(){
        var permisstions = permissionRepository.findAll();
       return  permisstions.stream().map(permissionMapper::toPermissionResponse).toList();
    }
    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
