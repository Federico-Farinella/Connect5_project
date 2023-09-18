package com.example.connect5_project.login.controller;

import com.example.connect5_project.take_booking.model.FootballPlayerDao;
import com.example.connect5_project.login.bean.LoginBeanRequest;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.exceptions.login_exceptions.LoginException;
import com.example.connect5_project.take_booking.model.FootballPlayer;
import com.example.connect5_project.take_booking.model.CurrentUser;

public class LoginController {

    public boolean loginVerify(LoginBeanRequest beanIn) throws MyException, LoginException {
        boolean ret;
        FootballPlayerDao dao = new FootballPlayerDao();

        FootballPlayer footballPlayer1;
        try {
            footballPlayer1 = dao.checkUser(beanIn);
        } catch (LoginException e) {
            throw new EmailNotRegisteredException("Email not registered");
        } catch (ConnectionDBException e) {
            throw new ConnectionDBException("Error connecting database");
        }
        if (footballPlayer1 == null) {
            ret = false;
        } else {
            ret = this.updateSession(footballPlayer1);
        }
        return ret;


    }

    public boolean updateSession(FootballPlayer footballPlayer) {
        try {
            CurrentUser.getInstance().setUser(footballPlayer);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
