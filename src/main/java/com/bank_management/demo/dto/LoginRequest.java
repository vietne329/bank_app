package com.bank_management.demo.dto;

public class LoginRequest {
    private String username;
    private String password;
    private String FacebookId;

    public LoginRequest(){
        super();
    }

    public LoginRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


    public String getFacebookId() {
        return FacebookId;
    }

    public void setFacebookId(String facebookId) {
        FacebookId = facebookId;
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
}
