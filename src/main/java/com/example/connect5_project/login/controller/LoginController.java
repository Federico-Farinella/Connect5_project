package com.example.connect5_project.login.controller;

import com.example.connect5_project.take_booking.model.FootballPlayerDao;
import com.example.connect5_project.login.bean.LoginBeanRequest;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.exceptions.login_exceptions.LoginException;
import com.example.connect5_project.utility.LoggingUser;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.FootballPlayer;
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

        FootballPlayer footballPlayer1;
        try {
            //user1 = dao.checkUser(user);
            footballPlayer1 = dao.checkUser(beanIn);

            ret = this.updateSession(footballPlayer1);


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

    public boolean updateSession(FootballPlayer footballPlayer) {
        try {
            CurrentUser.getInstance().setFirstName(footballPlayer.getFirstName());
            CurrentUser.getInstance().setLastName(footballPlayer.getLastName());
            CurrentUser.getInstance().setEmail(footballPlayer.getEmail());
            CurrentUser.getInstance().setNickName(footballPlayer.getNickName());
            //CurrentUser.getInstance().setId(user.getUserID());}
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
