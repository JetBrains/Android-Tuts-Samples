package com.example.PrefDroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class MatchSettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private ListPreference entryScoringType;
    private EditTextPreference entryTeam1;
    private EditTextPreference entryTeam2;

    public static final String ENTRY_SCORING_TYPE = "scoring";
    public static final String ENTRY_SCORING_TEAM1 = "team1";
    public static final String ENTRY_SCORING_TEAM2 = "team2";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

        // Get a reference to the preference widgets
        entryScoringType = (ListPreference) getPreferenceScreen().findPreference(ENTRY_SCORING_TYPE);
        entryTeam1 = (EditTextPreference) getPreferenceScreen().findPreference(ENTRY_SCORING_TEAM1);
        entryTeam2 = (EditTextPreference) getPreferenceScreen().findPreference(ENTRY_SCORING_TEAM2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Setup the initial values
        setSummaries();

        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Set new summary, when a preference value changes
        //if (key.equals(ENTRY_SCORING_TYPE) || ) {
            setSummaries();
        //}
    }


    // Private members
    private void setSummaries() {
        entryScoringType.setSummary(entryScoringType.getEntry().toString());
        entryTeam1.setSummary(entryTeam1.getText().toString());
        entryTeam2.setSummary(entryTeam2.getText().toString());
    }
}