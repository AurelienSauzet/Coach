package com.example.coach.modele;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class ProfilTest {
    // Création d'un profil : femme de 67kg, 1m65, 35 ans
    // Résultat de l'IMG correspondant = 32.4
    // Message correspondant = "Trop élevé"
    private Profil profil = new Profil(67, 165, 35, 0, new Date());
    private float img = (float)32.2;
    private String message = "Trop élevé";

    @Test
    public void testGetImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void testGetMessage() {
        assertEquals(message, profil.getMessage());
    }
}