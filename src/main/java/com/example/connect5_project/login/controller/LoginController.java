package com.example.connect5_project.login.controller;

import com.example.connect5_project.take_booking.model.FootballPlayerDao;
import com.example.connect5_project.login.bean.LoginBeanRequest;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.exceptions.login_exceptions.LoginException;
import com.example.connect5_project.utility.LoggingUser;
import com.example.connect5_project.take_booking.model.FootballPlayer;
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


    }

    public boolean updateSession(FootballPlayer footballPlayer) {
        try {
            CurrentUser.getInstance().setUser(footballPlayer);
            //CurrentUser.getInstance().setFirstName(footballPlayer.getFirstName());
            //CurrentUser.getInstance().setLastName(footballPlayer.getLastName());
            //CurrentUser.getInstance().setEmail(footballPlayer.getEmail());
            //CurrentUser.getInstance().setNickName(footballPlayer.getNickName());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
