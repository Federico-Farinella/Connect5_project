package com.example.connect5_project.bean;

import java.time.LocalDate;

public class DailyAvailabilityBeanIn {
    private LocalDate dateToSearch;

    public LocalDate getDateToSearch() {
        return dateToSearch;
    }

    public void setDateToSearch(LocalDate date_to_search) {
        this.dateToSearch = date_to_search;
    }
}
