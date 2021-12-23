package com.example.hellogit;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BookDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // parameter inisialisasi fragmen, mis. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    TextView header_detail_buku, judul_buku_db, pengarang_buku_db, informasi_harga_db,
            judul_deskripsi_db, deskripsi_buku_db, rating_buku_db, jumlah_rating_buku_db,
            jumlah_hal_buku_db, hal_buku_db, bahasa, bahasa_buku_db, btn_baca_db;
    // TODO: Rename and change types of parameters
    private String mParam1;

    public BookDetailsFragment() {
        // Diperlukan konstruktor publik kosong
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BookDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookDetailsFragment newInstance(String param1) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate tata letak untuk fragmen ini
        View h = inflater.inflate(R.layout.fragment_book_details, container, false);

        TextView judul_buku_db = h.findViewById(R.id.judul_buku_db);
        judul_buku_db.setText(mParam1);
        TextView deskripsi_buku_db = h.findViewById(R.id.deskripsi_buku_db);
        deskripsi_buku_db.setText(mParam1);

        // Custom Font
        header_detail_buku = (TextView) h.findViewById(R.id.header_detail_buku);
        judul_buku_db = (TextView) h.findViewById(R.id.judul_buku_db);
        pengarang_buku_db = (TextView) h.findViewById(R.id.pengarang_buku_db);
        informasi_harga_db = (TextView) h.findViewById(R.id.informasi_harga_db);
        judul_deskripsi_db = (TextView) h.findViewById(R.id.judul_deskripsi_db);
        deskripsi_buku_db = (TextView) h.findViewById(R.id.deskripsi_buku_db);
        rating_buku_db = (TextView) h.findViewById(R.id.rating_buku_db);
        jumlah_rating_buku_db = (TextView) h.findViewById(R.id.jumlah_rating_buku_db);
        jumlah_hal_buku_db = (TextView) h.findViewById(R.id.jumlah_hal_buku_db);
        hal_buku_db = (TextView) h.findViewById(R.id.hal_buku_db);
        bahasa = (TextView) h.findViewById(R.id.bahasa);
        bahasa_buku_db = (TextView) h.findViewById(R.id.bahasa_buku_db);
        btn_baca_db = (TextView) h.findViewById(R.id.btn_baca_db);

        // Custom Font
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsMedium.ttf");

        header_detail_buku.setTypeface(semibold);
        judul_buku_db.setTypeface(semibold);
        pengarang_buku_db.setTypeface(medium);
        informasi_harga_db.setTypeface(medium);
        judul_deskripsi_db.setTypeface(semibold);
        deskripsi_buku_db.setTypeface(regular);
        rating_buku_db.setTypeface(medium);
        jumlah_rating_buku_db.setTypeface(semibold);
        jumlah_hal_buku_db.setTypeface(medium);
        hal_buku_db.setTypeface(semibold);
        bahasa.setTypeface(medium);
        bahasa_buku_db.setTypeface(semibold);
        btn_baca_db.setTypeface(semibold);

        return h;
    }
}