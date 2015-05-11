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

        loadFragment(new GeneralPreferenceFragment());
    }

    public static class GeneralPreferenceFragment extends com.fnp.materialpreferences.PreferenceFragment {

        @Override
        public int addPreferencesFromResource() {
            return R.xml.preferences;
        }
    }
}
