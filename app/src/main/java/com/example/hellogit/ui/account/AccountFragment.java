package com.example.hellogit.ui.account;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hellogit.R;
import com.example.hellogit.SettingsActivity;

public class AccountFragment extends Fragment {

    TextView header_account, settings, about_menu;

    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View h = inflater.inflate(R.layout.fragment_account, container, false);

        header_account = (TextView) h.findViewById(R.id.header_account);
        settings = (TextView) h.findViewById(R.id.settings);
        about_menu = (TextView) h.findViewById(R.id.about_menu);

        // Custom Font
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsMedium.ttf");

        header_account.setTypeface(semibold);
        settings.setTypeface(medium);
        about_menu.setTypeface(medium);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengalihkan dari halaman fragment menuju ke SettingActivity
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            }
        });

        about_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return h;
    }

}