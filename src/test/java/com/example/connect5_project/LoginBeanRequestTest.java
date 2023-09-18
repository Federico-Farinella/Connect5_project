package com.example.connect5_project;

import com.example.connect5_project.login.bean.LoginBeanRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginBeanRequestTest {
    @Test
    public void settingEmail() {
        LoginBeanRequest beanRequest = new LoginBeanRequest();
        boolean isSetted = beanRequest.setEmail("federicogmail.com");
        assertTrue(isSetted);
    }
}
