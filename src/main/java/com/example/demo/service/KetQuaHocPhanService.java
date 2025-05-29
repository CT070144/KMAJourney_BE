package com.example.demo.service;

import com.example.demo.Mapper.KetQuaHocPhanMapper;
import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.entity.KetQua;
import com.example.demo.entity.SinhVien;
import com.example.demo.exception.ApplicationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.HocPhanRepository;
import com.example.demo.repository.KetQuaRepository;
import com.example.demo.repository.SinhVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KetQuaHocPhanService {

    SinhVienRepository sinhVienRepository;
    KetQuaRepository ketQuaRepository;

    public KetQuaHocPhanDTO getKetQua(String maSinhVien) {
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien).orElseThrow(()->new ApplicationException(ErrorCode.USER_NOT_EXIST));

        List<KetQua> list =   ketQuaRepository.findBySinhVien(sinhVien);

        return KetQuaHocPhanMapper.toKetQuaHocPhan(list);
    }
}
