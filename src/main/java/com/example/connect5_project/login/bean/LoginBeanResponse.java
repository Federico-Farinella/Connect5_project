package com.example.connect5_project.login.bean;

import com.example.connect5_project.take_booking.model.bookingsType_decorator.FootballPlayer;

public class LoginBeanResponse {
    private boolean success;
    private String response;
    private FootballPlayer footballPlayer;

    public LoginBeanResponse() {
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

    public FootballPlayer getUser() {
        return footballPlayer;
    }

    public void setUser(FootballPlayer footballPlayer) {
        this.footballPlayer = footballPlayer;
    }
}
