package com.example.demo.controller;


import com.example.demo.dto.response.APIResponse;

import com.example.demo.service.HocPhanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/hocphan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HocPhanController {
    HocPhanService hocPhanService;

    @GetMapping("/{maSV}")
    APIResponse<List<Object[]>> getUser(@PathVariable("maSV") String maSinhVien){

        System.out.println("helloSpring"+maSinhVien);
        return APIResponse.<List<Object[]>>builder()
                .result(hocPhanService.getKetQuaRepository(maSinhVien))
                .build();
    }
}
