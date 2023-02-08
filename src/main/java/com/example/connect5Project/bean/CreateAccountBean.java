package com.example.connect5Project.bean;

public class CreateAccountBean {
    Boolean validEmail;
    String name, surname, email, password;

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

    public Boolean isValidEmail(String email) {
        Boolean ret = true;
        if (!email.endsWith("@gmail.com"))
            ret = false;
        return ret;
    }

    public Boolean isValidPassword(String password){
        Boolean ret = true;
        if (password.length()<6) {
            ret = false;
        }
        return ret;
    }
}
