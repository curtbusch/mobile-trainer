package com.example.mobiletrainer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsFragment fragment = new SettingsFragment();

        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);

        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        View screen = this.getWindow().getDecorView();
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean darkMode = getPrefs.getBoolean("colour", false);

        if(darkMode) {
            screen.setBackgroundResource(getPrefs.getInt("background", R.color.differentBackground));
        }
        else {
            screen.setBackgroundResource(getPrefs.getInt("background", R.color.whiteBackground));
        }
    }


}
