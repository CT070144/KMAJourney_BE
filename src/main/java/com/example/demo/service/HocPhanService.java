package com.example.demo.service;

import com.example.demo.Mapper.KetQuaHocPhanMapper;
import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.entity.HocPhan;
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

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HocPhanService {
    KetQuaRepository ketQuaRepository;
    SinhVienRepository sinhVienRepository;
    HocPhanRepository hocPhanRepository;


    public List<HocPhan> getDanhSachHocPhan() {
        return hocPhanRepository.findAll();

    }
    public List<HocPhan> getHocPhanById(String maHocPhan){
        return hocPhanRepository.findAllById(Collections.singleton(maHocPhan));
    }
    public List<HocPhan> getHocPhanByName(String tenHocPhan){
        return hocPhanRepository.findByName(tenHocPhan);
    }
    public List<HocPhan> getHocPhanByHocKy(int hocky){
        return hocPhanRepository.findByhocky(hocky);
    }
    public List<HocPhan> getHocPhanBySoTinChi(int soTinChi){
        return hocPhanRepository.findBysotinchi(soTinChi);
    }
}
