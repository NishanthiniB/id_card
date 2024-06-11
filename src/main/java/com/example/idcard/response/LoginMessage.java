package com.example.idcard.response;

public class LoginMessage {

    private String message;
    private boolean success;
    private Long userId;
    private String username;
    private String role;

    public LoginMessage() {
    }

    public LoginMessage(String message, boolean success, Long userId, String username, String role) {
        this.message = message;
        this.success = success;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public LoginMessage(String s, boolean b) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
