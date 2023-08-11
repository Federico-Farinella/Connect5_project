package com.example.connect5_project.models;

import com.example.connect5_project.models.booking_Decorator.BasicBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;

    private List<BasicBooking> bookings;

    public User(String email) {
        this.email = email;
    }
    // devo togliere password da qui e mantenere solo info del tipo email e nickname nella sessione utente (CurrentUser)

    public User(String firstName, String lastName, String email, String nickName) {
        //this.firstName = new SimpleStringProperty(firstName);
        //this.lastName = new SimpleStringProperty(lastName);
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
