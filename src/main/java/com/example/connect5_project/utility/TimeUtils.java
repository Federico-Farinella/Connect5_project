package com.example.connect5_project.utility;

public class TimeUtils {
    public static String hourConverter(String hour) {
        String ret = "";
        switch (hour) {
            case "15" -> ret = "h15_16";
            case "16" -> ret = "h16_17";
            case "17" -> ret = "h17_18";
            case "18" -> ret = "h18_19";
            case "19" -> ret = "h19_20";
            case "20" -> ret = "h20_21";
            case "21" -> ret = "h21_22";
            case "22" -> ret = "h22_23";
        }
        return ret;
    }
}
