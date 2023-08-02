package com.example.connect5_project.utility;

import com.example.connect5_project.models.CentroSportivo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SportCenterListElements {
    private static ArrayList<CentroSportivo> results;

    //public SportCenterListElements(ArrayList<CentroSportivo> list) {
    //    this.risultati = list;
    //}
    public static boolean setList(ArrayList<CentroSportivo> list) {
        boolean done = false;
        if (!list.isEmpty()) {
            results = list;
            done = true;
        }
        return done;
    }

    public void setListOfElements(ResultSet rs) {

    }

     public static void resetList() {
        results.clear();
     }

    public static ArrayList<CentroSportivo> getLastResList() {
        return results;
    }

}
