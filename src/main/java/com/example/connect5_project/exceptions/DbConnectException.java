package com.example.connect5_project.exceptions;


public class DbConnectException extends MyException{

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
