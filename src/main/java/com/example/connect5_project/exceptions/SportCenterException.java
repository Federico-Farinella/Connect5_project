package com.example.connect5_project.exceptions;

import java.io.Serial;

public class SportCenterException extends MyException {
    public SportCenterException(String message) {
        super(message);
    }

    public SportCenterException(Throwable cause) {
        super(cause);
    }

    public SportCenterException(String message, Throwable cause) {
        super(" +++ " + message + " +++ " + cause);
    }
}
