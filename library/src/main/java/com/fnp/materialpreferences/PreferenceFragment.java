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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

@TargetApi(11)
public abstract class PreferenceFragment extends android.preference.PreferenceFragment {

    public abstract int addPreferencesFromResource();

    public static HashMap<String, PreferenceScreen> preferenceScreenHashMap = new HashMap<>();

    private PreferenceScreen mPreferenceScreen;

    public void savePreferenceScreen(PreferenceScreen preferenceScreen) {
        mPreferenceScreen = preferenceScreen;
    }

    @Override
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        super.setPreferenceScreen(preferenceScreen);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (addPreferencesFromResource() != -1) {
            addPreferencesFromResource(addPreferencesFromResource());
        }

        if (savedInstanceState != null) {
            if (getPreferenceScreen() != null) {
                Log.d("aaa", "aaa");
                for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                    Preference preference = getPreferenceScreen().getPreference(i);
                    if (preference instanceof PreferenceScreen) {
                        preferenceScreenHashMap.put(preference.getKey(),
                                (PreferenceScreen) preference);
                    }
                }

            } else {
                PreferenceScreen preferenceScreen = preferenceScreenHashMap
                        .get(savedInstanceState.getString("myFragmentTag"));
                if (preferenceScreen != null) {
                    this.setPreferenceScreen(preferenceScreen);
                    for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                        Preference preference = getPreferenceScreen().getPreference(i);

                        if (preference instanceof PreferenceScreen) {
                            preferenceScreenHashMap.put(preference.getKey(),
                                    (PreferenceScreen) preference);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putString("myFragmentTag", getPreferenceScreen().getKey());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v != null) {
            if (mPreferenceScreen != null && getPreferenceScreen() == null) {
                super.setPreferenceScreen(mPreferenceScreen);
            }
            ListView lv = (ListView) v.findViewById(android.R.id.list);
            lv.setPadding(0, 0, 0, 0);

            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                Preference preference = getPreferenceScreen().getPreference(i);

                if (preference instanceof PreferenceScreen) {
                    preference.setOnPreferenceClickListener(
                            new Preference.OnPreferenceClickListener() {
                                @Override
                                public boolean onPreferenceClick(Preference preference) {
                                    onPreferenceScreenClick(preference);
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
    public void onDestroy() {
        super.onDestroy();
        if (preferenceScreenHashMap.size() > 0) {
            preferenceScreenHashMap.clear();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Title of each fragment would be specify in android:title of the preference xml file
        ((PreferenceActivity) getActivity()).getSupportActionBar()
                .setTitle(getPreferenceScreen().getTitle());
    }

    public boolean onPreferenceScreenClick(@NonNull Preference preference) {

        // If the user has clicked on a preference screen, set up the action bar
        if (preference instanceof PreferenceScreen) {
            final Dialog dialog = ((PreferenceScreen) preference).getDialog();

            //Close the default view without toolbar and create our own Fragment version
            dialog.dismiss();

            NestedPreferenceFragment fragment = new NestedPreferenceFragment();

            //Save the preference screen so it can bet set when the transaction is done
            fragment.savePreferenceScreen(((PreferenceScreen) preference));

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                transaction.replace(R.id.content, fragment);
            } else {
                transaction.replace(android.R.id.content, fragment);
            }
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(preference.getKey());
            transaction.commitAllowingStateLoss();

            return true;
        }

        return false;
    }

}
