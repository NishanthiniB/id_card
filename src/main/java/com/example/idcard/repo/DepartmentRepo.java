package com.example.idcard.repo;

import com.example.idcard.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
Department findByName(String name);
}
