package com.example.connect5_project.models;

import com.example.connect5_project.dao.BookingDao;
import com.example.connect5_project.dao.DailyAvailabilityDao;

public class BookingService {
    private BookingDao bookDao;
    private DailyAvailabilityDao dailyDao;
    // Continua da qui... devo far si che prenotazione campo e aggiornamento disponibilita centro sportivo avvengano atomicamente
    // o scrivo una store procedure su db e poi la chiamo da qui (ChatGPT) o da qui chiamo i due metodi delle due dao uno dopo l altro
    // e se il secondo non va a buon fine farò una delete dell appuntamento che era stato preso dal primo metodo della prima dao

}
