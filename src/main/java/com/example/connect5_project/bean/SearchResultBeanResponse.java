package com.example.connect5_project.bean;

import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.SportCentersSearchResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultBeanResponse {
    private SportCentersSearchResults searchResults;

    public SportCentersSearchResults getSearchResults() {
        return this.searchResults;
    }
    public List<CentroSportivo> getListOfCenters() {
        return this.searchResults.getSportCentersSearchResults();
    }

    public void setListOfCenters(SportCentersSearchResults searchResultsCenters) {
        this.searchResults = searchResultsCenters;
    }

}
