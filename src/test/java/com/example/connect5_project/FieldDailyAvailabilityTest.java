package com.example.connect5_project;

import com.example.connect5_project.take_booking.model.FieldDailyAvailability;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.ResultSet;

public class FieldDailyAvailabilityTest {
    @Test
    public void setResultsetNull() {
        ResultSet rs= null;
        FieldDailyAvailability availability = new FieldDailyAvailability();
        availability.setDailyAvailability(rs);
        assertNull(availability.getDailyAvailability());
    }
}
