package com.fnp.materialpreferencessample;

import android.os.Bundle;

public class SettingsActivity extends com.fnp.materialpreferences.PreferenceActivity {

    @Override
    public String getToolbarName() {
        return getString(R.string.settings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFragment(new MyPreferenceFragment());
    }

    public static class MyPreferenceFragment extends com.fnp.materialpreferences.PreferenceFragment
    {
        @Override
        public int addPreferencesFromResource() {
            return R.xml.preferences;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            //Write here your findPreference listeners if you want any
        }
    }
}
