package com.example.connect5_project.take_booking.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FieldDailyAvailability {
    private Map<String, String> dailyAvailability;

     private String response;

     public FieldDailyAvailability() {

     }

     public FieldDailyAvailability(ResultSet res, String response) {
         this.setDailyAvailability(res);
         this.response = response;
     }

    public Map<String, String> getDailyAvailability() {
        return dailyAvailability;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setDailyAvailability(ResultSet rs) {
        Map<String, String> availability = new HashMap<>();
        try {
            if (rs.first()) {
                String from15to16 = rs.getString("h15_16");
                availability.put("15", from15to16);
                String from16to17 = rs.getString("h16_17");
                availability.put("16", from16to17);
                String from17to18 = rs.getString("h17_18");
                availability.put("17", from17to18);
                String from18to19 = rs.getString("h18_19");
                availability.put("18", from18to19);
                String from19to20 = rs.getString("h19_20");
                availability.put("19", from19to20);
                String from20to21 = rs.getString("h20_21");
                availability.put("20", from20to21);
                String from21to22 = rs.getString("h21_22");
                availability.put("21", from21to22);
                String from22to23 = rs.getString("h22_23");
                availability.put("22", from22to23);
            } else {
                System.out.println("Result set vuoto");
                availability.put("15", "1");
                availability.put("16", "1");
                availability.put("17", "1");
                availability.put("18", "1");
                availability.put("19", "1");
                availability.put("20", "1");
                availability.put("21", "1");
                availability.put("22", "1");
            }
        } catch (SQLException exception) {
            this.setResponse("Error reading data");
        }
        this.dailyAvailability = availability;
    }
}
