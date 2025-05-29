package com.example.demo.controller;


import com.example.demo.dto.response.APIResponse;

import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.entity.HocPhan;
import com.example.demo.entity.KetQua;
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

    @GetMapping("/subject-list")
    APIResponse<List<HocPhan>> getList(){
        return APIResponse.<List<HocPhan>>builder()
                .result(hocPhanService.getDanhSachHocPhan())
                .build();
    }
    @GetMapping("/{maHocPhan}")
    APIResponse<List<HocPhan>> getHocPhan(@PathVariable("maHocPhan") String maHocPhan){

        var response = hocPhanService.getHocPhanById(maHocPhan);

        if(!response.isEmpty()){
            return  APIResponse.<List<HocPhan>>builder()
                    .result(response)
                    .build();
        }
        return APIResponse.<List<HocPhan>>builder()
                .result(hocPhanService.getHocPhanByName(maHocPhan))
                .build();
    }
    @GetMapping("/filter/hocky/{hocky}")
    APIResponse<List<HocPhan>> filterWithHocKy(@PathVariable("hocky") int hocky){
        return APIResponse.<List<HocPhan>>builder()
                .result(hocPhanService.getHocPhanByHocKy(hocky))
                .build();
    }
    @GetMapping("/filter/tinchi/{sotinchi}")
    APIResponse<List<HocPhan>> filterWithTinChi(@PathVariable("sotinchi") int tinchi){
        return APIResponse.<List<HocPhan>>builder()
                .result(hocPhanService.getHocPhanBySoTinChi(tinchi))
                .build();
    }

}
