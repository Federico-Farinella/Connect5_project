package com.example.connect5_project.history;

import javafx.scene.Scene;

import java.util.Stack;

public class Navigate {
    public Stack<Scene> pages = new Stack<>();
    public int countPagesAfterLogin ;

    public void pushPage(Scene scene) {
        pages.push(scene);
    }

    public Stack<Scene> getPages() {
        return pages;
    }

    public int getCountPagesAfterLogin() {
        return countPagesAfterLogin;
    }

    public void setCountPagesAfterLogin(int countPagesAfterLogin) {
        this.countPagesAfterLogin = countPagesAfterLogin;
    }
}
