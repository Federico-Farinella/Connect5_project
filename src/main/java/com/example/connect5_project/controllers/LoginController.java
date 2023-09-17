package com.example.connect5_project.controllers;

import com.example.connect5_project.dao.FootballPlayerDao;
import com.example.connect5_project.bean.LoginBeanRequest;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.exceptions.login_exceptions.LoginException;
import com.example.connect5_project.models.LoggingUser;
import com.example.connect5_project.models.User;
import com.example.connect5_project.utility.CurrentUser;

public class LoginController {

    public boolean loginVerify(LoginBeanRequest beanIn) throws MyException, LoginException {
        boolean ret;

        String email = beanIn.getEmail();
        String password = beanIn.getPassword();
        LoggingUser user = new LoggingUser();
        user.setEmail(email);
        user.setPassword(password);
        FootballPlayerDao dao = new FootballPlayerDao();

        User user1;
        try {
            //user1 = dao.checkUser(user);
            user1 = dao.checkUser(beanIn);

            ret = this.updateSession(user1);


        } catch (LoginException e) {
            throw new EmailNotRegisteredException("Email not registered");
            //ret = false;
        } catch (ConnectionDBException e) {
            throw new ConnectionDBException("Error connecting database");
        }
        return ret;



        /*LoginBeanOut beanOut = dao.checkUser(user);

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
        return beanOut;*/
    }

    public boolean updateSession(User user) {
        try {
            CurrentUser.getInstance().setFirstName(user.getFirstName());
            CurrentUser.getInstance().setLastName(user.getLastName());
            CurrentUser.getInstance().setEmail(user.getEmail());
            CurrentUser.getInstance().setNickName(user.getNickName());
            //CurrentUser.getInstance().setId(user.getUserID());}
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
