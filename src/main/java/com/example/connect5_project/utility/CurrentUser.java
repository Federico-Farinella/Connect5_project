package com.example.connect5_project.utility;

import com.example.connect5_project.take_booking.model.bookingsType_decorator.FootballPlayer;

public class CurrentUser {
    private static CurrentUser currentUser;
    private FootballPlayer footballPlayer;
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;

    private CurrentUser() {
    }

    public static CurrentUser getInstance(){
        if (currentUser == null)
            currentUser = new CurrentUser();
        return currentUser;
    }

    public FootballPlayer getUser() {
        return footballPlayer;
    }

    public void setUser(FootballPlayer footballPlayer) {
        this.footballPlayer = footballPlayer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
