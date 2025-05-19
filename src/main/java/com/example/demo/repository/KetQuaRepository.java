package com.example.demo.repository;

import com.example.demo.entity.KetQua;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KetQuaRepository extends JpaRepository<KetQua, String> {
    @Query(
            value = "    SELECT  sv.ma_sinh_vien, sv.ten_sinh_vien, sv.lop, sv.khoa,\n        hp.ten_hoc_phan, kq.diem_thanh_phan1, kq.diem_thanh_phan2,\n        kq.diem_thi, kq.diem_tong_ket, kq.diem_chu, hp.hocky,hp.sotinchi\n    FROM ket_qua kq\n    JOIN sinh_vien sv ON sv.ma_sinh_vien = kq.ma_sinh_vien\n    JOIN hoc_phan hp ON hp.ma_hoc_phan = kq.ma_hoc_phan\n    WHERE kq.ma_sinh_vien = :maSinhVien\n",
            nativeQuery = true
    )
    List<Object[]> findKetQuaDTOByMaSinhVien(@Param("maSinhVien") String maSinhVien);
}
