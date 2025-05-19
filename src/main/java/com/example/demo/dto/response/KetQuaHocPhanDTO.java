package com.example.demo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Builder
public class KetQuaHocPhanDTO {
     String ten_sinh_vien;
     String ma_sinh_vien;
     String lop;
     String khoa;
     String ten_hoc_phan;
     Double diem_thanh_phan1;
     Double diem_thanh_phan2;
     Double diem_thi;
     Double diem_tong_ket;
     String diem_chu;
     int hocKy;
}