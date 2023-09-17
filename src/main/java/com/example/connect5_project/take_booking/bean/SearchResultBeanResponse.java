package com.example.connect5_project.take_booking.bean;

import com.example.connect5_project.take_booking.model.SportCenter;
import com.example.connect5_project.take_booking.model.SportCentersSearchResults;

import java.util.List;

public class SearchResultBeanResponse {
    private SportCentersSearchResults searchResults;

    public SportCentersSearchResults getSearchResults() {
        return this.searchResults;
    }
    public List<SportCenter> getListOfCenters() {
        return this.searchResults.getSportCentersSearchResults();
    }

    public void setListOfCenters(SportCentersSearchResults searchResultsCenters) {
        this.searchResults = searchResultsCenters;
    }

}
