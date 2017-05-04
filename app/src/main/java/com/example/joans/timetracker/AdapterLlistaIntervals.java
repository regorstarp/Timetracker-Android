package com.example.joans.timetracker;

/**
 * Created by rogerprats on 29/12/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterLlistaIntervals extends ArrayAdapter<String> {
    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();

    private Activity context;
    private ArrayList<Integer> icon;
    private ArrayList<String> durada;
    private ArrayList<String> dataInici;
    private List<DadesInterval> llistaDadesIntervals;
    public AdapterLlistaIntervals(Activity context, ArrayList<Integer> iconInterval,
                                   ArrayList<String> duradaInterval, List<DadesInterval>
                                          llistaDadesIntervals, ArrayList<String> dataInici) {
        super(context, R.layout.activity_element_llista_intervals, duradaInterval);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.icon=iconInterval;
        this.durada = duradaInterval;
        this.dataInici = dataInici;
        this.llistaDadesIntervals = llistaDadesIntervals;
    }

    public View getView(final int position,View view,ViewGroup parent) {
        Log.i(tag, "getView");
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_element_llista_intervals, null,true);
        ImageView iconeInterval = (ImageView) rowView.findViewById(R.id.iconInterval);
        TextView duradaInterval = (TextView) rowView.findViewById(R.id.durada);
        TextView dataIniciInterval = (TextView) rowView.findViewById(R.id.dataInici);
        iconeInterval.setImageResource(icon.get(position));
        duradaInterval.setText(durada.get(position));
        dataIniciInterval.setText(dataInici.get(position));
        return rowView;

    };
}