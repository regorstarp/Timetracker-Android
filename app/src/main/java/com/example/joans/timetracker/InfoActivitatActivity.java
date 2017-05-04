package com.example.joans.timetracker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.TextView;

public class InfoActivitatActivity extends AppCompatActivity {
    private final String tag = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activitat);
        Intent intent = getIntent();
        String nom = (String) intent.getSerializableExtra("Nom_Activitat");
        setTitle(nom);
        String desc = (String) intent.getSerializableExtra("Desc_Activitat");
        String dataInici = (String) intent.getSerializableExtra("Data_Inici");
        String dataFinal = (String) intent.getSerializableExtra("Data_Final");
        String durada = (String) intent.getSerializableExtra("Durada_Activitat");
        Boolean isProjecte = (Boolean) intent.getSerializableExtra("Is_Projecte");
        if (isProjecte || durada.equals("0s")){
            ((Button) findViewById(R.id.mostraIntervals)).setVisibility(View.INVISIBLE);
        }
        TextView nomProjecte = (TextView) findViewById(R.id.textNomActivitat);
        TextView descProjecte = (TextView) findViewById(R.id.textDescActivitat);
        TextView textDataInici = (TextView) findViewById(R.id.textDataInici);
        TextView textDataFinal = (TextView) findViewById(R.id.textDataFinal);
        TextView textDurada = (TextView) findViewById(R.id.textDurada);
        nomProjecte.setText(nom);
        descProjecte.setText(desc);
        textDataInici.setText(dataInici);
        textDataFinal.setText(dataFinal);
        textDurada.setText(durada);
    }
    public void mostraIntervals(View v){
        getApplicationContext().sendBroadcast(new Intent(
                LlistaActivitatsActivity.BAIXA_NIVELL));
        getApplicationContext().startActivity(new Intent(getApplicationContext(),
                LlistaIntervalsActivity.class));
    }
}