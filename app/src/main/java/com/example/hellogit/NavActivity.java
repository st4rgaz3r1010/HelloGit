package com.example.hellogit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hellogit.ui.account.AccountFragment;
import com.example.hellogit.ui.cart.CartFragment;
import com.example.hellogit.ui.collection.CollectionFragment;
import com.example.hellogit.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class NavActivity extends AppCompatActivity {

    // inisialisasi variabel
    SwitchCompat DM_switch;
    SharedPreferences sharedPreferences = null;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        // Tetapkan variabel
        DM_switch = findViewById(R.id.DM_switch);

        sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode", true);
        if (booleanValue){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            DM_switch.setChecked(true);
            setTheme(R.style.Theme_HelloGitDark);
        } else {
            setTheme(R.style.Theme_HelloGit);
        }

        DM_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // kondisi check
                    // Ketika tombol switch dalam kondisi checked
                    // Mengatur ke mode gelap
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    DM_switch.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", true);
                    editor.commit();
                    reset();
                    Toast.makeText(NavActivity.this, "Dark mode is Enabled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Ketika tombol switch dalam kondisi tidak checked
                    // Mengatur ke mode terang
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    DM_switch.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", false);
                    editor.commit();
                    reset();
                    Toast.makeText(NavActivity.this, "Dark mode is Disabled",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sembunyikan bilah navigasi bawah
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new ScrollHandler());

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

    private void reset() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Mengatur transisi animasi
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
                        case R.id.navigation_cart:
                            selectedFragment = new CartFragment();
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