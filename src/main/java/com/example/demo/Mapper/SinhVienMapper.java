package com.example.demo.Mapper;

import com.example.demo.dto.request.SinhVienRequest;
import com.example.demo.dto.response.SinhVienResponse;
import com.example.demo.entity.SinhVien;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SinhVienMapper {
    SinhVienResponse toSinhVienResponse(SinhVien sinhVien);
    SinhVien toSinhVien(SinhVienRequest request);
}
