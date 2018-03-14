package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by Jose A. Alvarado on 3/13/2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();
        int prefCount = preferenceScreen.getPreferenceCount();

        for (int pref = 0; pref < prefCount; pref++) {
            Preference currPref = preferenceScreen.getPreference(pref);
            if (!(currPref instanceof CheckBoxPreference)) {
                setPreferenceSummary(currPref, sharedPreferences.getString(currPref.getKey(), ""));
            }
        }
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        String valueStr = value.toString();

        if (preference instanceof ListPreference) {
            int currListPrefIndex = ((ListPreference) preference).findIndexOfValue(valueStr);
            preference.setSummary(((ListPreference) preference).getEntryValues()[currListPrefIndex]);
        } else if (preference instanceof EditTextPreference) {
            preference.setSummary(valueStr);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);

        if (!(pref instanceof CheckBoxPreference)) {
            setPreferenceSummary(pref, sharedPreferences.getString(key, ""));
        }
    }
}
