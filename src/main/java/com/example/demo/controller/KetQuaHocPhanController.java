package com.example.demo.controller;

import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.service.KetQuaHocPhanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ketqua")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KetQuaHocPhanController {
    KetQuaHocPhanService ketQuaHocPhanService;
    @GetMapping("/{maSV}")
    APIResponse<KetQuaHocPhanDTO> getUser(@PathVariable("maSV") String maSinhVien){
        return APIResponse.<KetQuaHocPhanDTO>builder()
                .result(ketQuaHocPhanService.getKetQua(maSinhVien))
                .build();
    }
}
