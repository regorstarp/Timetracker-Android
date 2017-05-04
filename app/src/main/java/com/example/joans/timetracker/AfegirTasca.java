package com.example.joans.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AfegirTasca extends AppCompatActivity {
    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();
    public static final String AFEGEIX_TASCA = "Afegeix_Tasca";
    /**
     * Estableix com a activitats a visualitzar les filles del projecte
     * arrel, així com els dos listeners que gestionen els
     * events de un click normal i un click llarg. El primer serveix per navegar
     * "cap avall" per l'arbre, o sigui, veure els fills d'un projecte o els
     * intervals d'una tasca. El segon per cronometrar, en cas que haguem clicat
     * sobre una tasca.
     *
     * @param savedInstanceState
     *            de tipus Bundle, però no el fem servir ja que el pas de
     *            paràmetres es fa via l'objecte aplicació
     *            <code>TimeTrackerApplication</code>.
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "onCreate");
        final EditText nomField = (EditText) findViewById(R.id.nomTascaInput);
        String nom = nomField.getText().toString();
        final EditText descField = (EditText) findViewById(R.id.descTascaInput);
        String desc = descField.getText().toString();

        Log.i(tag, "intent afegeix_tasca");
        Intent resposta = new Intent(AfegirTasca.AFEGEIX_TASCA);
        resposta.putExtra("Nom_Tasca", nom);
        resposta.putExtra("Descripcio_Tasca", desc);
        sendBroadcast(resposta);
        Log.d(tag, "enviat intent AFEGIR_TASCA ");


    }
    /**
     * Gestor de l'event de prémer la tecla 'enrera' del D-pad. El que fem es
     * anar "cap amunt" en l'arbre de tasques i projectes. Si el projecte pare
     * de les activitats que es mostren ara no és nul (n'hi ha), 'pugem' per
     * mostrar-lo a ell i les seves activitats germanes. Si no n'hi ha, paro el
     * servei, deso l'arbre (equivalent a parar totes les tasques que s'estiguin
     * cronometrant) i pleguem de la aplicació.
     */
    @Override
    public final void onBackPressed() {
        Log.i(tag, "onBackPressed");
        sendBroadcast(new Intent(LlistaActivitatsActivity.PUJA_NIVELL));
        Log.d(tag, "enviat intent PUJA_NIVELL");
        sendBroadcast(new Intent(LlistaActivitatsActivity.DONAM_FILLS));
        Log.d(tag, "enviat intent DONAM_FILLS");
    }
}