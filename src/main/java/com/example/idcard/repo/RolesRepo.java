package com.example.idcard.repo;

import com.example.idcard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
