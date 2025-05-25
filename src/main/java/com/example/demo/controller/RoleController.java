package com.example.demo.controller;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.PermisstionResponse;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public APIResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return APIResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }
    @GetMapping
    public APIResponse<List<RoleResponse>> getAll(){
        return APIResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{role}")
    public APIResponse<Void> create(@PathVariable String role){
        roleService.delete(role);
        return APIResponse.<Void>builder().build();
    }

}
