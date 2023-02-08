package com.example.connect_5_project;

import com.example.connect_5_project.DAO.FootballPlayerDao;
import com.example.connect_5_project.bean.LoginBeanIn;
import com.example.connect_5_project.bean.LoginBeanOut;
import com.example.connect_5_project.models.LoggingUser;
import com.example.connect_5_project.utility.CurrentUser;

public class Login {

    public Login() {
    }

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
                || beanOut.getResponse().equals("Driver to connect database not found")) {
            beanOut.setResponse("Error");
        } else if (beanOut.getResponse().equals("Match")) {
            /*String sessionId = UUID.randomUUID().toString();
            Cookie sessionCookie = new Cookie("sessionId", sessionId);
            HttpServletResponse response;*/
            CurrentUser currentUser = CurrentUser.getInstance();
            currentUser.setUser(beanOut.getUser());

        }

        return beanOut;
        // Continua dal FootballPlayerDao

        /*if (beanIn.getEmail().equals("myEmail"))
            //System.out.println("Email Corretta");
            return "Email Corretta";
        return "Email Non Corretta";*/
    }
}
