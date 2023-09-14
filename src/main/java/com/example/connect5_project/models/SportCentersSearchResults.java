package com.example.connect5_project.models;

import java.util.List;

public class SportCentersSearchResults {
    private List<CentroSportivo> sportCentersSearchResults;

    public SportCentersSearchResults(List<CentroSportivo> list) {
        this.setSportCentersSearchResults(list);
    }

    public List<CentroSportivo> getSportCentersSearchResults() {
        return sportCentersSearchResults;
    }

    public void setSportCentersSearchResults(List<CentroSportivo> listOfCenters) {
        this.sportCentersSearchResults = listOfCenters;
    }
}
