package com.example.joans.timetracker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class FormulariAfegirActivitat extends AppCompatActivity {
    public static final String AFEGEIX_TASCA = "Afegeix_Tasca";
    public static final String AFEGEIX_PROJECTE = "Afegeix_Projecte";
    private final String tag = this.getClass().getSimpleName();

    public Boolean isProgramadaChecked = false;
    public Boolean isLimitadaChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_activitat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tasca"));
        tabLayout.addTab(tabLayout.newTab().setText("Projecte"));
        tabLayout.getTabAt(0).setIcon(R.drawable.tasca);
        tabLayout.getTabAt(1).setIcon(R.drawable.project);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapterTabs adapter = new PageAdapterTabs
                (getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    public void clickSwitcherLimitada(View v){
        isLimitadaChecked = !isLimitadaChecked;
        Log.i(tag, "isLimitadaChecked " + isLimitadaChecked);
        if (isLimitadaChecked){
            findViewById(R.id.btnLimitada).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.btnLimitada).setVisibility(View.INVISIBLE);
        }
    }
    public void clickSwitcherProgramada(View v){
        isProgramadaChecked = !isProgramadaChecked;
        Log.i(tag, "isLimitadaChecked " + isProgramadaChecked);
        if (isProgramadaChecked){
            findViewById(R.id.btnProgramada).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.btnProgramada).setVisibility(View.INVISIBLE);
        }
    }
    public void mostratpDurada(View v){
        DialogFragment newFragment = new FormulariAfegirActivitat.TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void mostratpProgramada(View v){
        DialogFragment dialogFragment = new FormulariAfegirActivitat.TimePickerHora();
        dialogFragment.show(getSupportFragmentManager(), "picker_data_inicial");
    }
    public void afegirTasca(View v){
        final EditText nomField = (EditText) findViewById(R.id.nomTascaInput);
        String nom = nomField.getText().toString();
        final EditText descField = (EditText) findViewById(R.id.descTascaInput);
        String desc = descField.getText().toString();
        Log.i(tag, "intent afegeix_tasca");
        Intent resposta = new Intent(this.AFEGEIX_TASCA);
        resposta.putExtra("Nom_Tasca", nom);
        resposta.putExtra("Descripcio_Tasca", desc);
        sendBroadcast(resposta);
        Log.d(tag, "enviat intent AFEGIR_TASCA ");
        super.onBackPressed();
    }

    public void afegirProjecte(View v){
        final EditText nomField = (EditText) findViewById(R.id.nomProjecteInput);
        String nom = nomField.getText().toString();
        final EditText descField = (EditText) findViewById(R.id.descProjecteInput);
        String desc = descField.getText().toString();
        Log.i(tag, "intent afegeix_projecte");
        Intent resposta = new Intent(this.AFEGEIX_PROJECTE);
        resposta.putExtra("Nom_Projecte", nom);
        resposta.putExtra("Descripcio_Projecte", desc);
        sendBroadcast(resposta);
        Log.d(tag, "enviat intent AFEGIR_PROJECTE ");
        super.onBackPressed();
    }
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);
    int hora = 00;
    int minut = 00;
    @SuppressLint("ValidFragment")
    public  class TimePickerHora extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hora = hourOfDay;
            minut = minute;
            Button btnProgramada = (Button) findViewById(R.id.btnProgramada);
            btnProgramada.setText(hora + ":" + minut);

        }
    }
    public static TimePicker timePicker;

    @SuppressLint("ValidFragment")
    public  class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hora = hourOfDay;
            minut = minute;
            Button btnLimitada = (Button) findViewById(R.id.btnLimitada);
            btnLimitada.setText(hora + "h " + minut + "m");

        }
    }

}
