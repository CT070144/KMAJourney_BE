package com.example.demo.repository;

import com.example.demo.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HocPhanRepository extends JpaRepository<HocPhan, String> {
    @Query(value = "Select * from hoc_phan where ten_hoc_phan = :name OR ten_hoc_phan LIKE CONCAT('% ', :name, ' %') OR ten_hoc_phan LIKE CONCAT(:name, ' %') OR ten_hoc_phan LIKE CONCAT('% ',:name)"
            , nativeQuery = true)
    List<HocPhan> findByName(String name);
    List<HocPhan> findByhocky(int hocky);
    List<HocPhan> findBysotinchi(int hocky);
}
