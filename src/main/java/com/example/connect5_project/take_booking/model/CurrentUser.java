package com.example.connect5_project.take_booking.model;

public class CurrentUser {
    private static CurrentUser currentUser;
    private FootballPlayer footballPlayer;

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
}
