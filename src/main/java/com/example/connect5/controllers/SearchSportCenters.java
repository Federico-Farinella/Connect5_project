package com.example.connect5.controllers;

import com.example.connect5.DAO.CentriSportiviDAO;
import com.example.connect5.bean.SearchResultBean;

public class SearchSportCenters {
    private CentriSportiviDAO cDao;
    //static Connection conn;
    /*private String name;
    private String citta;
    private String via;
    private ArrayList<CentroSportivo> list;

    public SearchSportCenters(String name, String citta, String via) {
        this.name = name;
        this.citta = citta;
        this.via = via;
    }*/

    public SearchResultBean find(String name, String citta) throws Exception {
        //conn = JdbcConnect.getUserConnection();
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        return cDao.dbSearchCenters(name, citta);
        /*SearchResultBean resultBean = new SearchResultBean();
        resultBean.setListOfCenters(rs);
        return resultBean;*/
        //CONTINUA DA QUI!!! UNA VOLTA ACQUISITA LA LISTA ANDREBBE MANTENUTA TEMPORANEAMENTE IN CASO DI UN FUTURO BACK BUTTON
        // BISOGNA PASSARE LA LISTA ALLA CONTROLLERGUI E FARE IN MODO CHE I RISULTATI VENGANO LISTATI!!!
        //Map<String, String> list =
        //Map<String, Object> map = SearchResultBean.
    }

    public SearchResultBean findByCity(String city) throws Exception {
        //conn = JdbcConnect.getUserConnection();
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        //ResultSet rs = cDao.dbSearchCentersByCity(city);
        //SearchResultBean resultBean = new SearchResultBean();
        //resultBean.setListOfCenters(rs);
        return cDao.dbSearchCentersByCity(city);
    }

    //public ArrayList<SearchResult> find() {
    //
    //}
}
