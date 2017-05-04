package com.example.joans.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.Button;

public class PageAdapterTabs extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterTabs(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabAfegirTasca tab1 = new TabAfegirTasca();
                return tab1;
            case 1:
                TabAfegirProjecte tab2 = new TabAfegirProjecte();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}