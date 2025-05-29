package com.example.demo.Mapper;

import com.example.demo.dto.response.DiemHocPhanDTO;
import com.example.demo.dto.response.KetQuaHocPhanDTO;
import com.example.demo.entity.KetQua;
import com.example.demo.entity.SinhVien;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class KetQuaHocPhanMapper {

    public static KetQuaHocPhanDTO toKetQuaHocPhan(List<KetQua> list){
        KetQuaHocPhanDTO response = new KetQuaHocPhanDTO();
        SinhVien sinhVien = list.get(0).getSinhVien();
        response.setSinhVien(sinhVien);
        List<DiemHocPhanDTO> listDiem = new ArrayList<>();
        for(KetQua kq :list){
          DiemHocPhanDTO diemHocPhanDTO = DiemHocPhanDTO.builder()
                  .ten_hoc_phan(kq.getHocPhan().getTen_hoc_phan())
                  .soTinChi(kq.getHocPhan().getSotinchi())
                  .hocKy(kq.getHocPhan().getHocky())
                  .diem_thanh_phan1(kq.getDiemThanhPhan1())
                  .diem_thanh_phan2(kq.getDiemThanhPhan2())
                  .diem_thi(kq.getDiemThi())
                  .diem_tong_ket(kq.getDiemTongKet())
                  .diem_chu(kq.getDiemChu())
                  .build();
          listDiem.add(diemHocPhanDTO);

        }
response.setDiem(listDiem);
        return response;
    }
}
