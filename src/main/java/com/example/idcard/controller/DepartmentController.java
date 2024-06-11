package com.example.idcard.controller;

import com.example.idcard.model.Department;
import com.example.idcard.repo.DepartmentRepo;
import com.example.idcard.response.DepartmentMessage;
import com.example.idcard.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    DepartmentRepo repo;

    @GetMapping
    public ResponseEntity<List<DepartmentMessage>> getDepartments() {

        List<Department> departments = repo.findAll();
        List<DepartmentMessage> departmentMessages = new ArrayList<>();

        for (Department department : departments) {
            departmentMessages.add(new DepartmentMessage(department.getId(), department.getName()));
        }

        return ResponseEntity.ok(departmentMessages);
    }
}
