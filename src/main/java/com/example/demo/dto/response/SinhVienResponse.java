package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class SinhVienResponse {

    String ma_sinh_vien;
    String ten_sinh_vien;
    String lop;
    String khoa;
    String trang_thai;
}
