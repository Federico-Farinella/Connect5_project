package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.dao.CentriSportiviDAO;

public class SearchSportCenters {
    private CentriSportiviDAO cDao;

    public SearchResultBeanOut find(String name, String citta) throws Exception {
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        return cDao.dbSearchCenters(name, citta);

    }

    public SearchResultBeanOut findByCity(String city) throws Exception {
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        return cDao.dbSearchCentersByCity(city);
    }
}
