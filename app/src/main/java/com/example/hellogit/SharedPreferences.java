package com.example.hellogit;

import android.content.Context;

public class SharedPreferences {
    android.content.SharedPreferences sharedPreferences;

    public SharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("night",  0);
    }

    public void setNightModeState(Boolean modeState) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night_mode", modeState);
        editor.commit();
    }

    public Boolean loadNightMode() {
        Boolean modeState = sharedPreferences.getBoolean("night_mode", true);
        return modeState;
    }
}