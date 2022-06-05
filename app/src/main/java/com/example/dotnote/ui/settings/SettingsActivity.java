package com.example.dotnote.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.example.dotnote.R;
import com.example.dotnote.business_logic.MusicManager;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            SharedPreferences settings = requireContext().getSharedPreferences("root_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener((sharedPreferences, s) -> {
                editor.putBoolean(s, sharedPreferences.getBoolean(s, false));
                editor.apply();
                editor.commit();
            });
        }

    }

}