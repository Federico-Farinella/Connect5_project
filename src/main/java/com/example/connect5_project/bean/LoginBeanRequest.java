package com.example.connect5_project.bean;

public class LoginBeanRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        boolean ret = false;
        if (email.contains("@gmail.com") || email.contains("@virgilio.it") || email.contains("@hotmail.it")) {
            this.email = email;
            ret = true;
        }
        return ret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
