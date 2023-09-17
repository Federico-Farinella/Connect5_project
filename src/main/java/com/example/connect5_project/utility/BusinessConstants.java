package com.example.connect5_project.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BusinessConstants {
    public static final String APP_NAME = "Connect 5";
    // Le date selezionabili dall' utente per una prenotazione saranno comprese tra la data odierna
    // e i  DAY_TO_CHOOSE prossimi giorni
    public static int DAY_TO_CHOOSE = 2;

    public static void initialize() throws IOException{
        try (FileInputStream propsInput = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(propsInput);
            String dates = prop.getProperty("datePickerDays");
            DAY_TO_CHOOSE = Integer.parseInt(dates);
        } catch (IOException ex) {
            throw new IOException();
        }
    }
}
