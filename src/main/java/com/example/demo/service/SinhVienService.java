package com.example.demo.service;

import com.example.demo.Mapper.SinhVienMapper;
import com.example.demo.dto.request.SinhVienRequest;
import com.example.demo.dto.response.SinhVienResponse;
import com.example.demo.entity.SinhVien;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.SinhVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SinhVienService {
    SinhVienRepository sinhVienRepository;
    SinhVienMapper sinhVienMapper;

    public List<SinhVienResponse> getStudentList(){
       return sinhVienRepository.findAll().stream().map(sinhVienMapper::toSinhVienResponse).toList();
    }
    public List<SinhVienResponse> getStudentByMSV(String maSinhVien){
        var result = sinhVienRepository.findAllById(Collections.singleton(maSinhVien))

        .stream().map(sinhVienMapper::toSinhVienResponse).toList();
        return result;
    }
    public List<SinhVienResponse> getStudentByName(String name){
        var result = sinhVienRepository.findByTen(name).stream().map(sinhVienMapper::toSinhVienResponse).toList();
        return result;
    }
    public SinhVienResponse addStudent(SinhVienRequest request){
        boolean exists = sinhVienRepository.existsById(request.getMa_sinh_vien());
        if (exists) {
            throw new ApplicationException(ErrorCode.STUDENTCODE_EXISTED);
        }

        SinhVien sinhVien = sinhVienRepository.save(sinhVienMapper.toSinhVien(request));
        return sinhVienMapper.toSinhVienResponse(sinhVien);
    }
    public SinhVienResponse updateStudent(SinhVienRequest request){
        try {
            return sinhVienMapper.toSinhVienResponse(sinhVienRepository.save(sinhVienMapper.toSinhVien(request)));
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public void deleteStudent(String maSinhVien){
        try {
            sinhVienRepository.deleteById(maSinhVien);
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.FOREIGN_KEY);
        }
    }


}
