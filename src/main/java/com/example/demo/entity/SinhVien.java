package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="sinh_vien")
public class SinhVien {
    @Id
    String ma_sinh_vien;
    String ten_sinh_vien;
    String lop;
    String khoa;
    String trang_thai;

}
