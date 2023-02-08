package com.example.connect5.bean;

import com.example.connect5.models.User;

public class LoginBeanOut {
    String response;
    User user;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
