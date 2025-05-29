package com.example.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SinhVienRequest {

    String ma_sinh_vien;
    String ten_sinh_vien;
    String lop;
    String khoa;
    String trang_thai;

}
