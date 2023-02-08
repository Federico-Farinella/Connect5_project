package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.SearchResultBean;
import com.example.connect5_project.bean.SearchResultsBeanIn;
import com.example.connect5_project.dao.CentriSportiviDAO;
import com.example.connect5_project.models.CentroSportivo;

import java.util.ArrayList;

public class BookingController {
    private ArrayList<CentroSportivo> search_centers_results;

    public SearchResultBean searchCenters(SearchResultsBeanIn bean_in) {
        String search_mode = bean_in.getSearch_mode();
        SearchResultBean bean_to = new SearchResultBean();
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        switch (search_mode) {
            case ("Name") -> {
                bean_to = cDao.dbSearchCentersByName(bean_in.getName());
            }
            case ("City") -> {
                bean_to = cDao.dbSearchCentersByCity(bean_in.getCity());
            }
            case ("Name and city") -> {
                bean_to = cDao.dbSearchCenters(bean_in.getName(), bean_in.getCity());
            }
        }
        search_centers_results = bean_to.getListOfCenters();
        return bean_to;
    }
}
