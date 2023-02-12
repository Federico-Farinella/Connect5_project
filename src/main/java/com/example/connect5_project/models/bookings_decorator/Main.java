package com.example.connect5_project.models.bookings_decorator;

import com.example.connect5_project.models.Booking;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        /*Optional concrete = new ConcreteBasic(35f);
        Optional booking = new ConcreteWithReferee(new ConcreteWithTunics(new ConcreteBasic(40.5f)));
        String resp = booking.getDescription();
        //System.err.println(resp);
        System.out.println(booking.getDescription() + ". Price: " + booking.getPrice());
        //Logger log = Logger.getLogger(Main.class.getName());
        //log.info(resp);*/
        LocalDate date = LocalDate.now();
        String hour = "16";
        float f = 45.5f;

        Booking booking = new Booking(date, hour,  f);
        booking.setWithReferee();
        //booking.setWithTunics();

        System.out.println("Description: " + booking.getOptions().getDescription() + "\nPrice: " + booking.getOptions().getPrice());
        String description = booking.getOptions().getDescription();
        if (description.contains("with referee"))
            System.out.println("with referee");
        if (description.contains("with tunics"))
            System.out.println("with tunics");
    }
}
