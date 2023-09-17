package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.take_booking.model.SportCenter;

import java.util.List;

public class SportCentersSearchResults {
    private List<SportCenter> sportCentersSearchResults;

    public SportCentersSearchResults(List<SportCenter> list) {
        this.setSportCentersSearchResults(list);
    }

    public List<SportCenter> getSportCentersSearchResults() {
        return sportCentersSearchResults;
    }

    public void setSportCentersSearchResults(List<SportCenter> listOfCenters) {
        this.sportCentersSearchResults = listOfCenters;
    }
}
