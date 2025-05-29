package com.example.demo.repository;

import com.example.demo.entity.KetQua;
import java.util.List;

import com.example.demo.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KetQuaRepository extends JpaRepository<KetQua, String> {

    List<KetQua> findBySinhVien(SinhVien sinhVien);
}
