package com.example.joans.timetracker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class FormulariAfegirInterval extends AppCompatActivity {
    public static final String AFEGEIX_INTERVAL = "Afegeix_Interval";
    private final String tag = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_interval);
        TextView dataInicial = (TextView) findViewById(R.id.dataInici);
        TextView dataFinal = (TextView) findViewById(R.id.dataFinal);
        dataFinal.setText(endDay + "/" + endMonth+1 + "/" + endYear);
        dataInicial.setText(startDay + "/" + startMonth+1 + "/" + startYear);
        TextView durada = (TextView) findViewById(R.id.durada);
        durada.setText(hora + "h " +minut + "m");
        setTitle("Afegir Interval");
    }
    public void afegirInterval(View v){
        Log.i(tag, "intent afegeix_interval");
        Intent resposta = new Intent(this.AFEGEIX_INTERVAL);
        resposta.putExtra("Any_Inici", startYear);
        resposta.putExtra("Mes_Inici", startMonth);
        resposta.putExtra("Dia_Inici", startDay);
        resposta.putExtra("Any_Final", endYear);
        resposta.putExtra("Mes_Final", endMonth);
        resposta.putExtra("Dia_Final", endDay);
        resposta.putExtra("Hores", hora);
        resposta.putExtra("Minuts", minut);
        sendBroadcast(resposta);
        Log.d(tag, "enviat intent AFEGIR_INTERVAL ");
        super.onBackPressed();
    }

    public void mostradpDataInicial(View v){
        DialogFragment dialogFragment = new DataInicialPicker();
        dialogFragment.show(getSupportFragmentManager(), "picker_data_inicial");
    }
    public void mostradpDataFinal(View v){
        DialogFragment dialogFragment = new DataFinalPicker();
        dialogFragment.show(getSupportFragmentManager(), "picker_data_final");
    }
    public void mostratpDurada(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);
    int endYear = startYear;
    int endMonth = startMonth;
    int endDay = startDay;
    int hora = 00;
    int minut = 00;
    @SuppressLint("ValidFragment")
    public  class DataInicialPicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(FormulariAfegirInterval.this, this, startYear, startMonth, startDay);
            return dialog;

        }

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
            TextView dataInicial = (TextView) getActivity().findViewById(R.id.dataInici);
            dataInicial.setText(startDay + "/" + startMonth+1 + "/" + startYear);


        }
    }
    @SuppressLint("ValidFragment")
        public  class DataFinalPicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // TODO Auto-generated method stub
                // Use the current date as the default date in the picker
                DatePickerDialog dialog = new DatePickerDialog(FormulariAfegirInterval.this, this, startYear, startMonth, startDay);
                return dialog;

            }

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                // Do something with the date chosen by the user
                endYear = year;
                endMonth = monthOfYear;
                endDay = dayOfMonth;
                TextView dataFinal = (TextView) getActivity().findViewById(R.id.dataFinal);
                dataFinal.setText(endDay + "/" + endMonth+1 + "/" + endYear);


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
            TextView durada = (TextView) findViewById(R.id.durada);
            durada.setText(hora + ":" + minut);

        }
    }


}