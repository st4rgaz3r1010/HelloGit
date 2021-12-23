package com.example.hellogit.ui.collection;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hellogit.R;

public class CollectionFragment extends Fragment {

    TextView header_collection;

    private CollectionViewModel collectionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View h = inflater.inflate(R.layout.fragment_collection, container, false);

        header_collection = (TextView) h.findViewById(R.id.header_collection);

        // Custom Font
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsMedium.ttf");

        header_collection.setTypeface(semibold);

        return h;
    }
}