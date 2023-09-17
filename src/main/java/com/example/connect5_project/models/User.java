package com.example.connect5_project.models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;

    private List<Booking> bookings;

    public User(String email) {
        this.email = email;
    }
    // devo togliere password da qui e mantenere solo info del tipo email e nickname nella sessione utente (CurrentUser)

    public User(String firstName, String lastName, String email, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nickName = nickName;
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
