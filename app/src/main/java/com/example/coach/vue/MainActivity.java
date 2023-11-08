package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;


public class MainActivity extends AppCompatActivity {

    // Propriétés
    private Controle controle; // Appel du contrôle.
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Initialise les propriétés de MainActivity en les liant aux objets graphiques de activity_main.xml.
     */
    private void init(){
        // Liaison des objets graphiques
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        // Instanciation du contrôle
        controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfile();
    }

    /**
     * Affiche dans les champs les informations récupérées du profil.
     */
    private void recupProfile() {
        // Mise en place des 3 propriétés numériques.
        if (controle.getPoids() != null) {
            txtPoids.setText((controle.getPoids().toString()));
        }
        if (controle.getTaille() != null) {
            txtTaille.setText((controle.getTaille().toString()));
        }
        if (controle.getAge() != null) {
            txtAge.setText((controle.getAge().toString()));
        }
        if (controle.getSexe() != null) {
            // Mise en place du sexe.
            switch(controle.getSexe()) {
                case 0:
                    rdFemme.setChecked(true);
                    break;
                case 1:
                    rdHomme.setChecked(true);
                    break;
            }
        }

        btnCalc.performClick(); // Clic calculant l'IMG.
    }

    /**
     * Calcule l'IMG une fois que le bouton est appuyé.
     */
    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Récupère le poids, la taille et l'âge s'ils sont valides.
                int poids = 0;
                int taille = 0;
                int age = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch(Exception e){}

                // Vérifie le sexe sélectionné
                int sexe = 0;
                if(rdHomme.isChecked()) {
                    sexe = 1;
                }

                // Vérifie si les champs ont été rempli
                if (poids==0 || taille==0 || age==0){
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affiche le résultat du calcul de l'IMG avec le message et l'image correspondants.
     * @param poids Poids en kg
     * @param taille Taille en cm
     * @param age Âge
     * @param sexe
     */
    private void afficheResult(int poids, int taille, int age, int sexe) {
        controle.creerProfil(poids, taille, age, sexe, this);
        String message = controle.getMessage();
        float img = controle.getImg();
        switch(message) {
            case "Trop faible":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setText(String.format("%.01f", img) + " : IMG trop faible.");
                lblIMG.setTextColor(Color.RED);
                break;
            case "Normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setText(String.format("%.01f", img) + " : IMG normal.");
                lblIMG.setTextColor(Color.GREEN);
                break;
            case "Trop élevé":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setText(String.format("%.01f", img) + " : IMG trop élevé.");
                lblIMG.setTextColor(Color.RED);
                break;
        }

    }
}