package com.example.connect5_project.bean;

import java.time.LocalDate;

public class DailyAvailabilityBeanIn {
    private LocalDate date_to_search;

    public LocalDate getDate_to_search() {
        return date_to_search;
    }

    public void setDateToSearch(LocalDate date_to_search) {
        this.date_to_search = date_to_search;
    }
}
