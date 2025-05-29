package com.example.demo.controller;

import com.example.demo.dto.request.SinhVienRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.SinhVienResponse;
import com.example.demo.service.SinhVienService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/sinhvien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SinhVienController {
    SinhVienService sinhVienService;
    @GetMapping("/student-list")
    public APIResponse<List<SinhVienResponse>> getStudentList(){
         return APIResponse.<List<SinhVienResponse>>builder()
                 .result(sinhVienService.getStudentList())
                 .build();
    }
    @GetMapping("/{ma_sv}")
    public APIResponse<List<SinhVienResponse>> getStudent(@PathVariable("ma_sv") String maSinhVien){
        var result = sinhVienService.getStudentByMSV(maSinhVien);


        if(!result.isEmpty()){
            return APIResponse.<List<SinhVienResponse>>builder()
                    .result(result)
                    .build();
        }

        return APIResponse.<List<SinhVienResponse>>builder()
                .result(sinhVienService.getStudentByName(maSinhVien))
                .build();

    }
    @PostMapping("/add")
    public APIResponse<SinhVienResponse> addStudent(@RequestBody SinhVienRequest request){
        return APIResponse.<SinhVienResponse>builder()
                .result(sinhVienService.addStudent(request))
                .build();
    }
    @PutMapping("/update")
    public APIResponse<SinhVienResponse> updateStudent(@RequestBody SinhVienRequest request){
        return APIResponse.<SinhVienResponse>builder()
                .result(sinhVienService.updateStudent(request))
                .build();
    }
    @DeleteMapping("/{maSinhVien}")
    public APIResponse<Void> addStudent(@PathVariable("maSinhVien") String maSinhVien){
        sinhVienService.deleteStudent(maSinhVien);
        return APIResponse.<Void>builder()
                .build();
    }

}
