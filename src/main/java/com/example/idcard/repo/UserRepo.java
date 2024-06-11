package com.example.idcard.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.idcard.model.Department;
import com.example.idcard.model.User;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {

Optional<User> findOneByEmailAndPassword(String eamil, String password);
User findByEmail(String email);


    List<User> findByDepartment(Department department);

}
