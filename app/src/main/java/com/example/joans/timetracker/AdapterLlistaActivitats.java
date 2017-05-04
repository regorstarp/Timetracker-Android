package com.example.joans.timetracker;

/**
 * Created by rogerprats on 29/12/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nucli.Tasca;

public class AdapterLlistaActivitats extends ArrayAdapter<String> {
    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();
    private Activity context;
    private ArrayList<String> nom;
    private ArrayList<Integer> icon;
    private ArrayList<String> durada;
    private ArrayList<Integer> btn;
    private ImageButton activitatAccio;
    private List<DadesActivitat> llistaDadesActivitats;

    public AdapterLlistaActivitats(Activity context, ArrayList<String> nomActivitat,
                                   ArrayList<Integer> iconActivitat, ArrayList<String>
                                           duradaActivitat, ArrayList<Integer> btnActivitat,
                                   List<DadesActivitat> llistaDadesActivitats) {
        super(context, R.layout.activity_element_llista_activitats, nomActivitat);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.nom=nomActivitat;
        this.icon=iconActivitat;
        this.durada = duradaActivitat;
        this.btn = btnActivitat;
        this.llistaDadesActivitats = llistaDadesActivitats;

    }

    public View getView(final int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_element_llista_activitats, null,true);
        if(llistaDadesActivitats.get(position).isCronometreEngegat()){
            rowView.setBackgroundColor(Color.LTGRAY);
        }
        activitatAccio = (ImageButton) rowView.findViewById(R.id.btnActivitat);
        TextView nomActivitat = (TextView) rowView.findViewById(R.id.nomActivitat);
        ImageView iconeActivitat = (ImageView) rowView.findViewById(R.id.iconActivitat);
        TextView duradaActivitat = (TextView) rowView.findViewById(R.id.duradaActivitat);
        ImageButton btnActivitat = (ImageButton) rowView.findViewById(R.id.btnActivitat);
        nomActivitat.setText(nom.get(position));
        iconeActivitat.setImageResource(icon.get(position));
        duradaActivitat.setText(durada.get(position));
        btnActivitat.setImageResource(btn.get(position));

        //buto engegar/para comptador/ info
        activitatAccio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(tag, "click a activitat btn");
                Log.d(tag, "pos = " + position );

                if (llistaDadesActivitats.get(position).isTasca()) {
                    Intent inte;
                    if (!llistaDadesActivitats.get(position).isCronometreEngegat()) {
                        inte = new Intent(
                                LlistaActivitatsActivity.ENGEGA_CRONOMETRE);
                        Log.d(tag, "enviat intent ENGEGA_CRONOMETRE de "
                                + llistaDadesActivitats.get(position).getNom());
                    } else {
                        inte = new Intent(
                                LlistaActivitatsActivity.PARA_CRONOMETRE);
                        Log.d(tag, "enviat intent PARA_CRONOMETRE de "
                                + llistaDadesActivitats.get(position).getNom());
                    }
                    inte.putExtra("posicio", position);
                    context.sendBroadcast(inte);
                }
                else if (llistaDadesActivitats.get(position).isProjecte()){
                    DadesActivitat projecte_seleccionat = llistaDadesActivitats.get(position);
                    Intent intent = new Intent(context, InfoActivitatActivity.class);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS", Locale.GERMAN);
                    intent.putExtra("Nom_Activitat", projecte_seleccionat.getNom());
                    intent.putExtra("Desc_Activitat", projecte_seleccionat.getDescripcio());
                    intent.putExtra("Data_Inici", dateFormat.format(projecte_seleccionat.getDataInicial()));
                    intent.putExtra("Data_Final", dateFormat.format(projecte_seleccionat.getDataFinal()));
                    intent.putExtra("Durada_Activitat", projecte_seleccionat.toString());
                    intent.putExtra("Is_Projecte", projecte_seleccionat.isProjecte());
                    Log.d(tag, "inici de info projecte");
                    Log.d(tag, intent.toString());
                    context.startActivity(intent);
                }
            }
        });
        //click per baixar de nivell
        View.OnClickListener baixarNivell = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(LlistaActivitatsActivity.BAIXA_NIVELL);
                inte.putExtra("posicio", position);
                if (llistaDadesActivitats.get(position).isProjecte()) {
                    context.sendBroadcast(inte);
                    context.sendBroadcast(new Intent(
                            LlistaActivitatsActivity.DONAM_FILLS));
                    Log.d(tag, "enviat intent DONAM_FILLS");
                } else if (llistaDadesActivitats.get(position).isTasca()) {
                    /*context.startActivity(new Intent(context,
                            LlistaIntervalsActivity.class));*/
                    DadesActivitat projecte_seleccionat = llistaDadesActivitats.get(position);
                    Intent intent = new Intent(context, InfoActivitatActivity.class);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
                    intent.putExtra("Nom_Activitat", projecte_seleccionat.getNom());
                    intent.putExtra("Desc_Activitat", projecte_seleccionat.getDescripcio());
                    intent.putExtra("Data_Inici", dateFormat.format(projecte_seleccionat.getDataInicial()));
                    intent.putExtra("Data_Final", dateFormat.format(projecte_seleccionat.getDataFinal()));
                    intent.putExtra("Durada_Activitat", projecte_seleccionat.toString());
                    intent.putExtra("Is_Projecte", projecte_seleccionat.isProjecte());
                    Log.d(tag, "inici de info tasca");
                    Log.d(tag, intent.toString());
                    context.startActivity(intent);
                    // en aquesta classe ja es demanara la llista de fills
                } else {
                    // no pot ser!
                    assert false : "activitat que no es projecte ni tasca";
                }

            }
        };
        iconeActivitat.setOnClickListener(baixarNivell);
        duradaActivitat.setOnClickListener(baixarNivell);
        nomActivitat.setOnClickListener(baixarNivell);
        return rowView;
    };

}