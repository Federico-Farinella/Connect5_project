package com.example.connect5_project.utility;

public class SharedStateSingletonCLI {
    private static SharedStateSingletonCLI instance;
    private boolean redirecting;

    private SharedStateSingletonCLI() {
        // Costruttore privato per impedire l'istanziazione diretta.
    }

    public static SharedStateSingletonCLI getInstance() {
        if (instance == null) {
            instance = new SharedStateSingletonCLI();
            instance.setRedirecting(false);
        }
        return instance;
    }

    public boolean isRedirecting() {
        return redirecting;
    }

    public void setRedirecting(boolean redirecting) {
        this.redirecting = redirecting;
    }
}
