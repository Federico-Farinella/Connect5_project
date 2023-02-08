package com.example.connect_5.history;

import javafx.scene.Scene;

import java.util.Stack;

public class Navigate {
    public Stack<Scene> pages = new Stack<>();

    public void pushPage(Scene scene) {
        pages.push(scene);
    }

    public Stack<Scene> getPages() {
        return pages;
    }
}
