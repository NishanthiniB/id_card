package com.example.idcard.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

public class UserDto {


    private Long id;

    private String username;

    private String password;

    private String email;

    private String department;
    private long phnNumber;
    private String address;
    private MultipartFile photo;
    private String bloodGroup;

    private String role;

    public long getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(long phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String email,
                   String department, long phnNumber, String address, MultipartFile photo, String bloodGroup, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
        this.phnNumber = phnNumber;
        this.address = address;
        this.photo = photo;
        this.bloodGroup = bloodGroup;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", phnNumber=" + phnNumber +
                ", address='" + address + '\'' +
                ", photo=" + photo +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
