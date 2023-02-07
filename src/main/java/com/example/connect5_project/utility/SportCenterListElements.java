package com.example.connect5_project.utility;

import com.example.connect5_project.models.CentroSportivo;

import java.util.ArrayList;

public class SportCenterListElements {
    private static ArrayList<CentroSportivo> risultati;

    //public SportCenterListElements(ArrayList<CentroSportivo> list) {
    //    this.risultati = list;
    //}
    public static boolean setList(ArrayList<CentroSportivo> list) {
        boolean done = false;
        if (!list.isEmpty()) {
            risultati = list;
            done = true;
        }
        return done;
    }

     public static void resetList() {
        risultati.clear();
     }

    public static ArrayList<CentroSportivo> getLastResList() {
        return risultati;
    }

}
