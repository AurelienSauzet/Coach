package com.example.coach.modele;

import java.io.Serializable;

public class Profil implements Serializable {

    // Constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    // Propriétés
    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img = 0;
    private String message = "";

    /**
     * Constructeur valorisant directement le poids, la taille, l'âge et le sexe.
     * @param poids Poids en kg
     * @param taille Taille en cm
     * @param age Âge
     * @param sexe 0 pour femme, 1 pour homme
     */
    public Profil(int poids, int taille, int age, int sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    // Getters
    public int getPoids() {
        return poids;
    }
    public int getTaille() {
        return taille;
    }
    public int getAge() {
        return age;
    }
    public int getSexe() {
        return sexe;
    }

    /**
     * Calcule l'IMG (s'il est vide) selon le poids, la taille, l'âge et le sexe du profil.
     * @return img
     */
    public float getImg() {
        if (img == 0) {
            float tailleCm = ((float)taille)/100;
            img = (float)((1.2 * poids/(tailleCm*tailleCm)) + (0.23 * age) - (10.83 * sexe) - 5.4);
        }
        return img;
    }

    /**
     * Retourne un message selon l'IMG
     * @return "Trop faible", "Normal", "Trop élevé"
     */
    public String getMessage() {
        if (message.equals("")) {
            img = getImg();
            switch(sexe){
                case 0:
                    if (img < minFemme){
                        message = "Trop faible";
                    }
                    else if (img > maxFemme){
                        message = "Trop élevé";
                    }
                    else {
                        message = "Normal";
                    }
                case 1:
                    if (img < minHomme){
                        message = "Trop faible";
                    }
                    else if (img > maxHomme){
                        message = "Trop élevé";
                    }
                    else {
                        message = "Normal";
                    }
                break;
            }
        }
        return message;
    }
}
