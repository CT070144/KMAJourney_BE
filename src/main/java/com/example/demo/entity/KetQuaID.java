package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data

public class KetQuaID implements Serializable {
    private String maSinhVien;
    private String maHocPhan;

    public KetQuaID() {}

    public KetQuaID(String maSinhVien, String maHocPhan) {
        this.maSinhVien = maSinhVien;
        this.maHocPhan = maHocPhan;
    }

    // equals & hashCode bắt buộc phải có
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KetQuaID)) return false;
        KetQuaID that = (KetQuaID) o;
        return Objects.equals(maSinhVien, that.maSinhVien) &&
                Objects.equals(maHocPhan, that.maHocPhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSinhVien, maHocPhan);
    }
}
