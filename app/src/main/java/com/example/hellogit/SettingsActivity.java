package com.example.hellogit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    //inisialisasi variabel
    SwitchCompat DM_switch;
    SharedPreferences sharedPreferences = null;
    android.content.SharedPreferences prefs;
    android.content.SharedPreferences.Editor editor;
    Context context;

    TextView header_settings, title_item_1, sub_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        sharedPreferences = new SharedPreferences(SettingsActivity.this);
        if (sharedPreferences.loadNightMode() == true) {
            setTheme(R.style.Theme_HelloGitDark);
        } else {
            setTheme(R.style.Theme_HelloGit);
        }
        setting();

        // Menyembunyikan AppBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void setting() {
        // Tetapkan variabel
        DM_switch = findViewById(R.id.DM_switch);
        if (sharedPreferences.loadNightMode() == true) {
            DM_switch.setChecked(true);
        }

        DM_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // kondisi check
                    // Ketika tombol switch dalam kondisi checked
                    // Mengatur ke mode gelap
                    sharedPreferences.setNightModeState(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                    reset();
                    Toast.makeText(SettingsActivity.this,"Mengaktifkan Mode Gelap",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Ketika tombol switch dalam kondisi tidak checked
                    // Mengatur ke mode terang
                    sharedPreferences.setNightModeState(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                    reset();
                    Toast.makeText(SettingsActivity.this, "Menonaktifkan Mode Gelap",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), NavActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Mengatur transisi animasi
    }

    @Override
    protected void onResume() {
        setting();
        super.onResume();
    }
}