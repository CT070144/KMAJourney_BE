package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(
        name = "ket_qua"
)
public class KetQua {

    @EmbeddedId
    private KetQuaID id;

    @ManyToOne
    @MapsId("maHocPhan")
    @JoinColumn(name = "ma_hoc_phan")
    private HocPhan hocPhan;

    @ManyToOne
    @JoinColumn(name = "ma_sinh_vien")
    @MapsId("maSinhVien")
    private SinhVien sinhVien;
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