package com.lab.demo.dto;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String email;
    private String passw;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
}
