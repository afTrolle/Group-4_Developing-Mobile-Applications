package com.group4.paladar.Fragments.views;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.group4.paladar.R;


/**
 * Created by Surface on 2016-02-25.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }




}
