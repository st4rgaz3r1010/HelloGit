package com.example.hellogit;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hellogit.ui.account.AccountFragment;
import com.example.hellogit.ui.collection.CollectionFragment;
import com.example.hellogit.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class NavActivity extends AppCompatActivity {

    //inisialisasi variabel
    SwitchCompat DM_switch;
    SharedPreferences sharedPreferences = null;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = new SharedPreferences(NavActivity.this);
        if (sharedPreferences.loadNightMode() == true) {
            setTheme(R.style.Theme_HelloGitDark);
        } else {
            setTheme(R.style.Theme_HelloGit);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        setting();

        // Sembunyikan bilah navigasi bawah
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new ScrollHandler());

        // Menyembunyikan AppBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Fragment fragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Mengubah apa pun yang ada di tampilan fragment_container dengan fragment ini.
        transaction.replace(R.id.fragment_container, fragment, "fragment_home");
        transaction.commit();
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
                } else {
                    // Ketika tombol switch dalam kondisi tidak checked
                    // Mengatur ke mode terang
                    sharedPreferences.setNightModeState(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                    reset();
                }
            }
        });
    }

    private void reset() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Mengatur transisi animasi
    }

    @Override
    protected void onResume() {
        setting();
        super.onResume();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_collection:
                            selectedFragment = new CollectionFragment();
                            break;
                        case R.id.navigation_account:
                            selectedFragment = new AccountFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}