package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class DiemHocPhanDTO {

    String ten_hoc_phan;
    Double diem_thanh_phan1;
    Double diem_thanh_phan2;
    Double diem_thi;
    Double diem_tong_ket;
    String diem_chu;
    int hocKy;
    int soTinChi;


}