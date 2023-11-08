package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {

    // Propriétés
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveProfil";
    private static Context context;
    private static AccesLocal accesLocal;


    /**
     * Constructeur du singleton Controle
     * @param context Contexte
     */
    private Controle(Context context) {
        super();
        //recupSerialisation(context);
        accesLocal = AccesLocal.getInstance(context);
        profil = accesLocal.recupDernier();
    }

    /**
     * Retourne l'instance unique du contrôle.
     * S'il est vide, l'instance est crée avant d'être retournée.
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if (instance == null){
            Controle.instance = new Controle(context);
        }
        return instance;
    }

    /**
     * Instancie un profil.
     * @param poids Poids en kg
     * @param taille Taille en cm
     * @param age Âge
     * @param sexe 0 pour femme, 1 pour homme
     * @param context Contexte pour la sérialisation
     */
    public void creerProfil(int poids, int taille, int age, int sexe, Context context){
        profil = new Profil(poids, taille, age, sexe, new Date());
        //Serializer.serialize(nomFic, profil, context);
        accesLocal.ajout(profil);
    }

    /**
     * Récupère les informations de l'objet sérialisé.
     * (Ici, les infos du profil entrées précédemment.)
     * @param context Contexte de l'objet
     */
    private static void recupSerialisation(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }

    /**
     * Getter sur le poids de l'instance de profil, s'il existe.
     * @return poids Poids du profil en kg
     */
    public Integer getPoids(){
        if (profil != null){
            return profil.getPoids();
        }
        else {
            return null;
        }
    }

    /**
     * Getter sur la taille de l'instance de profil, s'il existe.
     * @return taille Taille du profil en cm
     */
    public Integer getTaille(){
        if (profil != null){
            return profil.getTaille();
        }
        else {
            return null;
        }
    }

    /**
     * Getter sur l'âge' de l'instance de profil, s'il existe.
     * @return age Âge du profil
     */
    public Integer getAge(){
        if (profil != null){
            return profil.getAge();
        }
        else {
            return null;
        }
    }

    /**
     * Getter sur le sexe de l'instance de profil, s'il existe.
     * @return sexe 0 pour une femme, 1 pour un homme
     */
    public Integer getSexe(){
        if (profil != null){
            return profil.getSexe();
        }
        else {
            return null;
        }
    }

    /**
     * Getter sur l'IMG de l'instance de profil, s'il existe. Sinon retourne 0
     * @return img IMG du profil
     */
    public float getImg(){
        if (profil != null){
            return profil.getImg();
        }
        else {
            return 0;
        }
    }

    /**
     * Getter sur le message de l'instance de profil.
     * @return message Message du profil qui accompagne l'IMG
     */
    public String getMessage(){
        if (profil != null) {
            return profil.getMessage();
        }
        else {
            return "";
        }
    }
}
