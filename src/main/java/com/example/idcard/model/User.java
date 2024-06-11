package com.example.idcard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name="UserLogin")
public class User {

    @Id
    @Column(name="user_id", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="user_name", length = 50)
    private String username;
    @Column(name="password",length = 255)
    private String password;
    @Column(name="user_email",length = 30,unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role roles;

    @Column(name="phn_num", length=10)
    private long phnNumber;

    @Column(name="address", length=50)
    private String address;

    @Column(name="blood_group")
    private String bloodGroup;
    @Column(name="approved")
    private boolean approved;
    @Column(name="approval_date")
    private LocalDateTime approvalDate;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="photo")
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User(Long id, String username, String password, String email, Department department, Role roles, long phnNumber, String address, String bloodGroup,
                boolean approved, LocalDateTime approvalDate, String imageUrl, byte[] photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
        this.roles = roles;
        this.phnNumber = phnNumber;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.approved = approved;
        this.approvalDate = approvalDate;
        this.imageUrl = imageUrl;
        this.photo = photo;
    }

    public User() {

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

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


    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", roles=" + roles +
                ", phnNumber=" + phnNumber +
                ", address='" + address + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", approved=" + approved +
                ", approvalDate=" + approvalDate +
                ", imageUrl='" + imageUrl + '\'' +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}


