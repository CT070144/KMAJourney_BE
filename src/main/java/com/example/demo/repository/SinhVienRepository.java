package com.example.demo.repository;

import com.example.demo.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien,String> {
    @Query(value = "SELECT * FROM sinh_vien WHERE ten_sinh_vien = :name OR ten_sinh_vien LIKE CONCAT('% ', :name)", nativeQuery = true)
    List<SinhVien> findByTen(@Param("name") String name);

}
