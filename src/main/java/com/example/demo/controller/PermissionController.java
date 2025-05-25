package com.example.demo.controller;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.PermisstionResponse;
import com.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public APIResponse<PermisstionResponse> create(@RequestBody PermissionRequest request){
        return APIResponse.<PermisstionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }
    @GetMapping
    public APIResponse<List<PermisstionResponse>> getAll(){
        return APIResponse.<List<PermisstionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    public APIResponse<Void> create(@PathVariable String permission){
        permissionService.delete(permission);
        return APIResponse.<Void>builder().build();
    }

}
