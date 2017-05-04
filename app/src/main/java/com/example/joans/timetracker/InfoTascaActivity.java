package com.example.joans.timetracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoTascaActivity extends AppCompatActivity {
    private final String tag = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activitat);
        Intent intent = getIntent();
        String nom = (String) intent.getSerializableExtra("Nom_Tasca");
        setTitle(nom);
        String desc = (String) intent.getSerializableExtra("Desc_Tasca");
        String dataInici = (String) intent.getSerializableExtra("Data_Inici");
        String dataFinal = (String) intent.getSerializableExtra("Data_Final");
        String durada = (String) intent.getSerializableExtra("Durada_Tasca");
        TextView nomTasca = (TextView) findViewById(R.id.textNomActivitat);
        TextView descTasca = (TextView) findViewById(R.id.textDescActivitat);
        TextView textDataInici = (TextView) findViewById(R.id.textDataInici);
        TextView textDataFinal = (TextView) findViewById(R.id.textDataFinal);
        TextView textDurada = (TextView) findViewById(R.id.textDurada);
        nomTasca.setText(nom);
        descTasca.setText(desc);
        textDataInici.setText(dataInici);
        textDataFinal.setText(dataFinal);
        textDurada.setText(durada);
    }
}