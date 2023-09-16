package com.example.connect5_project.controllers;

import com.example.connect5_project.dao.FootballPlayerDao;
import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.models.LoggingUser;
import com.example.connect5_project.utility.CurrentUser;

public class LoginController {

    public LoginBeanOut loginVerify(LoginBeanIn beanIn) {
        String email = beanIn.getEmail();
        String password = beanIn.getPassword();
        LoggingUser user = new LoggingUser();
        user.setEmail(email);
        user.setPassword(password);
        FootballPlayerDao dao = new FootballPlayerDao();
        LoginBeanOut beanOut = dao.checkUser(user);

        if (beanOut.getResponse().equals("Config file not found") || beanOut.getResponse().equals("Config file not loaded")
                || beanOut.getResponse().equals("Error with database connection")
                || beanOut.getResponse().equals("Error while creating the statement")
                || beanOut.getResponse().equals("Driver to connect database not found")
                || beanOut.getResponse().equals("DB Connection failed")) {
            beanOut.setResponse("Error");
        } else if (beanOut.getResponse().equals("Match")) {
            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.setUser(beanOut.getUser());

        }
        return beanOut;
    }
}
