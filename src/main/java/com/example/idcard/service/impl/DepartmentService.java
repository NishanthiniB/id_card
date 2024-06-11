package com.example.idcard.service.impl;

import com.example.idcard.model.Department;
import com.example.idcard.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repo;
    public Department getDepartmentById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public List<Department> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        try{
            departments = repo.findAll();
        }
        catch (Exception e){
            System.out.println("EXCEPTION OCCURRED :: "+e.getMessage());
        }
        return departments;
    }
}

