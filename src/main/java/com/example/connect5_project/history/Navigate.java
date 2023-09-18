package com.example.connect5_project.history;

import javafx.scene.Scene;

import java.util.Deque;
import java.util.LinkedList;

public class Navigate {
    public Deque<Scene> pages = new LinkedList<>();
    public int countPagesAfterLogin ;

    public void pushPage(Scene scene) {
        pages.push(scene);
    }

    public Deque<Scene> getPages() {
        return pages;
    }

    public int getCountPagesAfterLogin() {
        return countPagesAfterLogin;
    }

    public void setCountPagesAfterLogin(int countPagesAfterLogin) {
        this.countPagesAfterLogin = countPagesAfterLogin;
    }
}
