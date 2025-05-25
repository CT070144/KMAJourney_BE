package com.example.demo.repository;

import com.example.demo.entity.Role;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepostiory extends JpaRepository<Role,String> {
}
