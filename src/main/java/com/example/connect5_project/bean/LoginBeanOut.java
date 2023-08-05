package com.example.connect5_project.bean;

import com.example.connect5_project.models.User;

public class LoginBeanOut {
    private boolean success;
    private String response;
    private User user;

    public LoginBeanOut() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
