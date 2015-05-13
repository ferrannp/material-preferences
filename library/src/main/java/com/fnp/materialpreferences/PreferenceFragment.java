package com.fnp.materialpreferences;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

@TargetApi(11)
public abstract class PreferenceFragment extends android.preference.PreferenceFragment {

    public abstract int addPreferencesFromResource();

    private PreferenceScreen mPreferenceScreen;

    @Override
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        mPreferenceScreen = preferenceScreen;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (addPreferencesFromResource() != -1) {
            addPreferencesFromResource(addPreferencesFromResource());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v != null) {
            if (mPreferenceScreen != null) {
                super.setPreferenceScreen(mPreferenceScreen);
            }
            ListView lv = (ListView) v.findViewById(android.R.id.list);
            lv.setPadding(0, 0, 0, 0);

            ArrayList<Preference> preferenceList = getPreferenceScreens(getPreferenceScreen(),
                    new ArrayList<Preference>());
            for (Preference preference : preferenceList) {
                if (preference instanceof PreferenceScreen) {
                    //PreferenceScreen can't be extended so we change the layout here
                    if(preference.getLayoutResource() != R.layout.preference_material) {
                        preference.setLayoutResource(R.layout.preference_material);
                    }
                    preference.setOnPreferenceClickListener(
                            new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            onPreferenceTreeClick(null, preference);
                            return true;
                        }
                    });
                }
            }
        }
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO crash on configuration change
        ((PreferenceActivity) getActivity()).getSupportActionBar()
                .setTitle(getPreferenceScreen().getTitle());
    }

    private ArrayList<Preference> getPreferenceScreens(Preference p, ArrayList<Preference> list) {
        if (p instanceof PreferenceCategory || p instanceof PreferenceScreen) {
            PreferenceGroup pGroup = (PreferenceGroup) p;
            if (p instanceof PreferenceScreen) {
                list.add(p);
            }
            int pCount = pGroup.getPreferenceCount();
            for (int i = 0; i < pCount; i++) {
                getPreferenceScreens(pGroup.getPreference(i), list);
            }
        }
        return list;
    }


    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         @NonNull Preference preference) {

        // If the user has clicked on a preference screen, set up the action bar
        if (preference instanceof PreferenceScreen) {
            final Dialog dialog = ((PreferenceScreen) preference).getDialog();

            //Close the default view without toolbar and create our own Fragment version
            dialog.dismiss();

            NestedPreferenceFragment fragment = new NestedPreferenceFragment();

            fragment.setPreferenceScreen(((PreferenceScreen) preference));

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragment);
            //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(":android:prefs");
            transaction.commitAllowingStateLoss();

            return true;
        }

        return false;
    }

}
