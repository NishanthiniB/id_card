package com.example.idcard.service.impl;

import com.example.idcard.dto.LoginDto;
import com.example.idcard.dto.UserDto;
import com.example.idcard.model.User;
import com.example.idcard.response.LoginMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

//     String addUser(UserDto userDto);

     LoginMessage logInUser(LoginDto loginDto);

//     ResponseEntity<?> insert(String email);

     ResponseEntity<?> register(UserDto userDto);



     ResponseEntity<?> viewById(Long userId);

     ResponseEntity<?> viewAll();
     ResponseEntity<?> deleteUser(Long userId);
     ResponseEntity<?> approveUser(Long userId);

     ResponseEntity<?> uploadImage(Long userId, MultipartFile file);

     ResponseEntity<?> getUsersWithoutDetails();
}
