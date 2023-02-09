package com.example.connect5_project.bean;

public class CreateAccountBean {
    Boolean validEmail;
    String name;
    String surname;
    String email;
    String password;

    public CreateAccountBean(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com");

    }

    public boolean isValidPassword(String password){
        return password.length() >= 6;
    }
}
