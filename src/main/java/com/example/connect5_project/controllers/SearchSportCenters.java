package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.dao.SportCenterDAO;

public class SearchSportCenters {
    private SportCenterDAO cDao;

    public SearchResultBeanOut find(String name, String citta) throws Exception {
        SportCenterDAO cDao = new SportCenterDAO();
        return cDao.dbSearchCenters(name, citta);

    }

    public SearchResultBeanOut findByCity(String city) throws Exception {
        SportCenterDAO cDao = new SportCenterDAO();
        return cDao.dbSearchCentersByCity(city);
    }
}
