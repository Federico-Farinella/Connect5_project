package com.example.connect5_project.bean;

public class SearchResultsBeanRequest {
    String searchMode;
    String name;
    String city;

    public String getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(String searchMode) {
        this.searchMode = searchMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCli(String searchType, String field) {
        switch (searchType) {
            case ("Name") -> {
                this.setSearchMode(searchType);
                this.setName(field);
            }
            case ("City") -> {
                this.setSearchMode(searchType);
                this.setCity(field);
            }
        }
    }
}
