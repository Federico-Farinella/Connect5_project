package com.example.connect5_project.exceptions;

import java.io.Serial;

public class DbConnectException extends MyException{
    //@Serial
    //private static final long serialVersionUID = 1;

    public DbConnectException(String message) {
        super(message);
    }

    public DbConnectException(Throwable cause) {
        super(cause);
    }

    public DbConnectException(String message, Throwable cause) {
        super(" +++ " + message + " +++ " + cause);
    }
}
