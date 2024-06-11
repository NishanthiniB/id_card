package com.example.idcard.controller;

import com.example.idcard.dto.LoginDto;
import com.example.idcard.dto.UserDto;
import com.example.idcard.response.LoginMessage;
import com.example.idcard.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        return service.register(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody LoginDto loginDto){
        LoginMessage message = service.logInUser(loginDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/viewById/{id}")
    public ResponseEntity<?> viewUsersById(@PathVariable Long id) {
        return service.viewById(id);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewAllUsers() {
        return service.viewAll();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return service.deleteUser(userId);
    }

    @PostMapping("/approve/{userId}")
    public ResponseEntity<?> approveUser(@PathVariable Long userId) {
        return service.approveUser(userId);
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        logger.info("FILE UPLOAD IMAGE FOR USER :: {} FROM FILE :: {}", userId, file.getOriginalFilename());
        return new ResponseEntity<>(service.uploadImage(userId, file), HttpStatus.OK);
    }
    @GetMapping("/without-details")
    public ResponseEntity<?> getUsersWithoutDetails() {
        return service.getUsersWithoutDetails();
    }

}
