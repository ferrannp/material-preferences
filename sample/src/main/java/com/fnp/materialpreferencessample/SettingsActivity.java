package com.fnp.materialpreferencessample;

import android.os.Bundle;

public class SettingsActivity extends com.fnp.materialpreferences.PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) { //TODO do this in the library
            loadFragment(new MyPreferenceFragment());
        }
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
