package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.bean.SearchResultsBeanIn;
import com.example.connect5_project.dao.CentriSportiviDAO;
import com.example.connect5_project.models.CentroSportivo;

import java.util.ArrayList;

public class BookingController {
    private ArrayList<CentroSportivo> centers_results_list;
    private CentroSportivo choosen_center;

    /*public void setCentersResultsList(ArrayList<CentroSportivo> centers_results_list) {
        this.centers_results_list = centers_results_list;
    }*/

    public CentroSportivo getChoosenCenter() {
        return choosen_center;
    }

    public void setChoosenCenter(String name) {
        //this.choosen_center = choosen_center;
        for (CentroSportivo center : centers_results_list) {
            if (center.getName() == name)
                choosen_center = center;
        }
    }

    public SearchResultBeanOut searchCenters(SearchResultsBeanIn bean_in) {
        String search_mode = bean_in.getSearch_mode();
        SearchResultBeanOut bean_to = new SearchResultBeanOut();
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
        centers_results_list = bean_to.getListOfCenters();
        return bean_to;
    }
}
