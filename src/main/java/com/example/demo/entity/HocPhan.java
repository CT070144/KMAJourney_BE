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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hoc_phan")
public class HocPhan {
    @Id
    String ma_hoc_phan;
    String ten_hoc_phan;
    int sotinchi;
    int hocky;
}
