package com.example.hellogit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

@SuppressWarnings("ALL")
public class Preferences extends PreferenceActivity {

    //inisialisasi variabel
    SwitchCompat DM_switch;
    SharedPreferences sharedPreferences = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setting();
    }

    private void setting() {

        SharedPreferences sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = sharedPreferences.getBoolean("DARK", true);
        if (booleanValue) { // kondisi check
                    // Ketika tombol switch dalam kondisi checked
                    // Mengatur ke mode gelap
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("DARK", true);
                    editor.commit();
                    reset();
                    Toast.makeText(this, "Dark mode is Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // Ketika tombol switch dalam kondisi tidak checked
                    // Mengatur ke mode terang
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("DARK", false);
                    editor.commit();
                    reset();
                    Toast.makeText(this, "Dark mode is Disabled", Toast.LENGTH_SHORT).show();
                }

        final SwitchPreference switchPreference = (SwitchPreference) findPreference("DARK");
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preferences, Object obj) {
                if ((Boolean) obj) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchPreference.setChecked(true);
                    Toast.makeText(getApplicationContext(), "Dark mode is Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchPreference.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Dark mode is Disabled", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        setting();
        super.onResume();
    }

    private void reset() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Mengatur transisi animasi
    }
}