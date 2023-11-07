package com.example.coach.vue;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.coach.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainActivityTest {
    // Déclaration de l'activity de lancement du test.
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    /**
     * Test fonctionnel sur l'entrée d'un profil,
     * et sur l'affichage du résultat d'un IMG élevé.
     */
    @Test
    public void scenario() {
        // Sélection des paramètres: Femme pesant 65kg, mesurant 1,65m, âgée de 35 ans.
        onView(withId(R.id.rdFemme)).perform(click());
        onView(withId(R.id.txtPoids)).perform(typeText("65"), closeSoftKeyboard());
        onView(withId(R.id.txtTaille)).perform(typeText("165"), closeSoftKeyboard());
        onView(withId(R.id.txtAge)).perform(typeText("35"), closeSoftKeyboard());
        onView(withId(R.id.btnCalc)).perform(click());
        SystemClock.sleep(5000);
    }
}