package com.example.PrefDroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MatchSettings
{
    public String team1;
    public String team2;
    public Boolean isDouble;
    public int scoringType;

    @Override
    public String toString()
    {
        return String.format("%s - %s, %d", team1, team2, scoringType);
    }

    public static MatchSettings loadFromPreferences(Context context)
    {
        MatchSettings settings = new MatchSettings();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        settings.team1 = preferences.getString("team1", "First");
        settings.team2 = preferences.getString("team2", "Second");
        settings.isDouble = preferences.getBoolean("isdouble", false);
        settings.scoringType = Integer.valueOf(preferences.getString("scoring", "1"));
        return settings;
    }
}
