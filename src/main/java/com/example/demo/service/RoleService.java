package com.example.demo.service;

import com.example.demo.Mapper.RoleMapper;
import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepostiory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepostiory roleRepostiory;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
      return   roleMapper.toRoleResponse(roleRepostiory.save(role));

    }
    public List<RoleResponse> getAll(){
        return roleRepostiory.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }
    public void delete(String name){
        roleRepostiory.deleteById(name);
    }
}
