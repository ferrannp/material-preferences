package com.fnp.materialpreferences;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

            for (int i = 0; i < mPreferenceScreen.getPreferenceCount(); i++) {
                Preference preference = mPreferenceScreen.getPreference(i);

                if (preference instanceof PreferenceScreen) {
                    preference.setOnPreferenceClickListener(
                            new Preference.OnPreferenceClickListener() {
                                @Override
                                public boolean onPreferenceClick(Preference preference) {
                                    onPreferenceTreeClick(null, preference);
                                    return true;
                                }
                            });
                }

                //Apply custom layouts on pre-Lollipop
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    if (preference instanceof PreferenceScreen && preference.getLayoutResource()
                            != R.layout.preference_material) {
                        preference.setLayoutResource(R.layout.preference_material);
                    } else if (preference instanceof PreferenceCategory &&
                            preference.getLayoutResource() != R.layout.preference_category) {
                        preference.setLayoutResource(R.layout.preference_category);

                        PreferenceCategory category
                                = (PreferenceCategory) preference;
                        for (int j = 0; j < category.getPreferenceCount(); j++) {
                            Preference basicPreference = category.getPreference(j);
                            if (!(basicPreference instanceof PreferenceCategory
                                    || basicPreference instanceof PreferenceScreen)) {
                                if (basicPreference.getLayoutResource()
                                        != R.layout.preference_material_widget) {
                                    basicPreference
                                            .setLayoutResource(R.layout.preference_material_widget);
                                }
                            }
                        }
                    }
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
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                transaction.replace(R.id.content, fragment);
            }else{
                transaction.replace(android.R.id.content, fragment);
            }
            //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(":android:prefs");
            transaction.commitAllowingStateLoss();

            return true;
        }

        return false;
    }

}
