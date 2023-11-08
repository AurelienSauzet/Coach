package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    // Propriétés
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD; // Permet d'intéragir avec la BDD
    private static AccesLocal instance;
    private SQLiteDatabase bd; // La BDD locale

    /**
     * Constructeur du singleton AccesLocal
     * @param context Contexte
     */
    private AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /** Instancie la classe AccesLocal si ce n'est pas déjà fait,
     * puis retourne l'unique instance d'AccesLocal.
     * @param context Contexte
     * @return instance Unique instance de AccesLocal
     */
    public static AccesLocal getInstance(Context context) {
        if (instance == null) {
            instance = new AccesLocal(context);
        }
        return instance;
    }

    /**
     * Permet d'ajouter une nouvelle table "profil" à la BDD,
     * en insérant les propriétés du profil avec ces types:
     * poids - int ; taille - int ; age - int ; sexe - int ; dateMesure - String
     * @param profil Profil demandant une table
     */
    public void ajout(Profil profil) {
        // Création de la table
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Récupère les propriétés du profil
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        values.put("dateMesure", profil.getDateMesure().toString());
        // Insère les propriétés du profil dans une table
        bd.insert("profil", null, values);
        bd.close();
    }

    /**
     * Récupère le dernier profil dans la table.
     * @return Profil
     */
    public Profil recupDernier() {
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        String req = "SELECT * FROM profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if (!curseur.isAfterLast()) {
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date","*********** date="+dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }
        curseur.close();
        bd.close();
        return profil;
    }
}
