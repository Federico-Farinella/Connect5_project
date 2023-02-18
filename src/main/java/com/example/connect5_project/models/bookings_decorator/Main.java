package com.example.connect5_project.models.bookings_decorator;

import com.example.connect5_project.models.Booking;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.User;

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
        /*LocalDate date = LocalDate.now();
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
            System.out.println("with tunics");*/

        User user = new User("Alessandro", "Greco", "alegreco@gmail.com", "ciao", "alegreco");
        CentroSportivo center = new CentroSportivo("Sport Village", "Rome", "via Oceano Pacifico", "manager@gmail.com", "image.png", 40f);
        Booking booking = new Booking(center, user, LocalDate.now(), "17");
        System.out.println("Step 1 description :" + booking.getOptional().getDescription() + ". Price : " + booking.getOptional().getPrice());
        booking.setWithReferee();
        System.out.println("Step 2 description :" + booking.getOptional().getDescription() + ". Price : " + booking.getOptional().getPrice());
        booking.setWithTunics();
        System.out.println("Step 3 description :" + booking.getOptional().getDescription() + ". Price : " + booking.getOptional().getPrice());

    }
}
