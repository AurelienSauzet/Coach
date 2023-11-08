package com.example.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe réunissant plusieurs méthodes pour des objectifs diverses,
 * spécifiques à l'application.
 */
public abstract class MesOutils {

    /**
     * Convertit une date au format String en une date de class Date.
     * A l'entrée, la date est au format: 'yyyy-MM-dd HH:mm:ss'.
     * En sortie, la date est au format: 'EEE MMM dd hh:mm:ss 'GMT+00' yyyy'.
     * @param uneDate Date de classe String
     * @return Date de classe Date
     */
    public static Date convertStringToDate(String uneDate){
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convertit une date de classe Date en une date de classe String.
     * A l'entrée, la date est au format: 'EEE MMM dd hh:mm:ss 'GMT+00' yyyy'.
     * En sortie, la date est au format: 'yyyy-MM-dd HH:mm:ss'.
     * @param uneDate Date de classe Date
     * @return Date de classe String
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }
}
