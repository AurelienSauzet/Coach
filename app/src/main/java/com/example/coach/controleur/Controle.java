package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {

    // Propriétés
    private static Controle instance = null;
    private static Profil profil;

    // Constructeur de Controle.
    private Controle() {
        super();
    }

    /**
     * Retourne l'instance unique du contrôle.
     * S'il est vide, l'instance est crée avant d'être retournée.
     * @return instance
     */
    public static final Controle getInstance(){
        if (instance == null){
            Controle.instance = new Controle();
        }
        return instance;
    }

    /**
     * Instancie un profil.
     * @param poids en kg
     * @param taille en cm
     * @param age
     * @param sexe 0 pour femme, 1 pour homme
     */
    public void creerProfil(int poids, int taille, int age, int sexe){
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * Getter sur l'IMG de l'instance de profil, s'il existe. Sinon retourne 0
     * @return img du profil
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
     * @return message du profil
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
