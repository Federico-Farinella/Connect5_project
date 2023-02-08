package com.example.connect_5.utility;

import com.example.connect_5.models.User;

public class CurrentUser {
    private static CurrentUser currentUser;
    private User user;

    private CurrentUser() {
    }

    public static CurrentUser getInstance(){
        if (currentUser == null)
            currentUser = new CurrentUser();
        return currentUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
