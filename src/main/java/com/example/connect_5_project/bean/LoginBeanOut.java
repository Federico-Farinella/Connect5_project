package com.example.connect_5_project.bean;

import com.example.connect_5_project.models.User;

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
