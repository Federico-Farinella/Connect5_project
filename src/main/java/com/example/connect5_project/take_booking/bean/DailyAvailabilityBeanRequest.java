package com.example.connect5_project.take_booking.bean;

import java.time.LocalDate;

public class DailyAvailabilityBeanRequest {
    private LocalDate dateToSearch;

    public LocalDate getDateToSearch() {
        return dateToSearch;
    }

    public void setDateToSearch(LocalDate dateToSearch) {
        this.dateToSearch = dateToSearch;
    }
}
