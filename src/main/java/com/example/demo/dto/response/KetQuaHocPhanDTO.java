package com.example.demo.dto.response;

import com.example.demo.entity.SinhVien;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KetQuaHocPhanDTO {
     SinhVien sinhVien;
     List<DiemHocPhanDTO> diem;
}