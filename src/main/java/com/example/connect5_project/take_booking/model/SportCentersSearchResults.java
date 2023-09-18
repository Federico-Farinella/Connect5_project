package com.example.connect5_project.take_booking.model;

import java.util.List;

public class SportCentersSearchResults {
    private List<SportCenter> sportCentersResults;

    public SportCentersSearchResults(List<SportCenter> list) {
        this.setSportCentersSearchResults(list);
    }

    public List<SportCenter> getSportCentersSearchResults() {
        return sportCentersResults;
    }

    public void setSportCentersSearchResults(List<SportCenter> listOfCenters) {
        this.sportCentersResults = listOfCenters;
    }
}
