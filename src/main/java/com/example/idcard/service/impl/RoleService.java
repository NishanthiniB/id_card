package com.example.idcard.service.impl;

import com.example.idcard.model.Role;
import com.example.idcard.repo.RolesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private RolesRepo repo;

    public Role createRole(Role request) {
        Role role = new Role();
        role.setName(request.getName());
       // BeanUtils.copyProperties(request,role);
        return repo.save(role);
    }
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try{
            roles = repo.findAll();
        }
        catch (Exception e){
            System.out.println("EXCEPTION OCCURRED :: "+e.getMessage());
        }
        return roles;
    }
}
