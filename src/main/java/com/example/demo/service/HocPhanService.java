package com.example.demo.service;

import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.repository.KetQuaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HocPhanService {
    KetQuaRepository ketQuaRepository;


    public List<Object[]> getKetQuaRepository(String maSinhVien) {
       return ketQuaRepository.findKetQuaDTOByMaSinhVien(maSinhVien);
    }
}
