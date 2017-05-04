package com.example.joans.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class TabAfegirTasca extends Fragment {
    private final String tag = this.getClass().getSimpleName();
    public View rootView;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final String tag = this.getClass().getSimpleName();
        return inflater.inflate(R.layout.tab_afegir_tasca, container, false);
    }


}