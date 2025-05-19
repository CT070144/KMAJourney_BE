package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "ket_qua"
)
public class KetQua {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "ma_hoc_phan"
    )
    private String maHocPhan;
    @Column(
            name = "ma_sinh_vien"
    )
    private String maSinhVien;
    @Column(
            name = "diem_thanh_phan1"
    )
    private Double diemThanhPhan1;
    @Column(
            name = "diem_thanh_phan2"
    )
    private Double diemThanhPhan2;
    @Column(
            name = "diem_thi"
    )
    private Double diemThi;
    @Column(
            name = "diem_tong_ket"
    )
    private Double diemTongKet;
    @Column(
            name = "diem_chu"
    )
    private String diemChu;
}