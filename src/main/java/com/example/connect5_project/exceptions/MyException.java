package com.example.connect5_project.exceptions;

import java.io.Serial;

public class MyException extends Exception {
    @Serial
    private static final long serialVersionUID = 0;

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause) {
        super(" +++ " + message + " +++ " + cause);
    }
}
