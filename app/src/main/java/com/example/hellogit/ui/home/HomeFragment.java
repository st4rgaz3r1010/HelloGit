//Coding by Muhammad Rifqi Nugraha - Kelompok Coding

package com.example.hellogit.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellogit.R;
import com.example.hellogit.recyclerview.Book;
import com.example.hellogit.recyclerview.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    RecyclerView recyclerview;
    List<Book> mData;
    BookAdapter bookAdapter;
    TextView nama_profil, desc_profil, search_text, bagian_terbaru, the_magic, progress_buku_1,
            the_martian, progress_buku_2, judul_buku_3s, progress_buku_3, filter_semua_buku,
            filter_komik, filter_novel, filter_fantasi, filter_dongeng, bagian_trending,
            penulis_buku_trend_1, judul_buku_trend_1, penulis_buku_trend_2, judul_buku_trend_2,
            penulis_buku_trend_3, judul_buku_trend_3, daftar_buku;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View h = inflater.inflate(R.layout.fragment_home, container, false);
        //Mendapatkan referensi untuk recyclerView
        recyclerview = h.findViewById(R.id.recyclerview);
        // Membuat adapter
        bookAdapter = new BookAdapter(mData, getContext());
        // Mengatur adapter
        recyclerview.setAdapter(bookAdapter);
        //Mengatur atribut ukuran item lebih optimize
        recyclerview.setHasFixedSize(true);
        //Mengatur layoutManager
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        nama_profil = (TextView) h.findViewById(R.id.nama_profil);
        desc_profil = (TextView) h.findViewById(R.id.desc_profil);
        search_text = (TextView) h.findViewById(R.id.search_text);
        bagian_terbaru = (TextView) h.findViewById(R.id.bagian_terbaru);
        the_magic = (TextView) h.findViewById(R.id.the_magic);
        progress_buku_1 = (TextView) h.findViewById(R.id.progress_buku_1);
        the_martian = (TextView) h.findViewById(R.id.the_martian);
        progress_buku_2 = (TextView) h.findViewById(R.id.progress_buku_2);
        judul_buku_3s = (TextView) h.findViewById(R.id.judul_buku_3s);
        progress_buku_3 = (TextView) h.findViewById(R.id.progress_buku_3);
        filter_semua_buku = (TextView) h.findViewById(R.id.filter_semua_buku);
        filter_komik = (TextView) h.findViewById(R.id.filter_komik);
        filter_novel = (TextView) h.findViewById(R.id.filter_novel);
        filter_fantasi = (TextView) h.findViewById(R.id.filter_fantasi);
        filter_dongeng = (TextView) h.findViewById(R.id.filter_dongeng);
        bagian_trending = (TextView) h.findViewById(R.id.bagian_trending);
        penulis_buku_trend_1 = (TextView) h.findViewById(R.id.penulis_buku_trend_1);
        judul_buku_trend_1 = (TextView) h.findViewById(R.id.judul_buku_trend_1);
        penulis_buku_trend_2 = (TextView) h.findViewById(R.id.penulis_buku_trend_2);
        judul_buku_trend_2 = (TextView) h.findViewById(R.id.judul_buku_trend_2);
        penulis_buku_trend_3 = (TextView) h.findViewById(R.id.penulis_buku_trend_3);
        judul_buku_trend_3 = (TextView) h.findViewById(R.id.judul_buku_trend_3);
        daftar_buku = (TextView) h.findViewById(R.id.daftar_buku);

        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsMedium.ttf");

        nama_profil.setTypeface(semibold);
        desc_profil.setTypeface(regular);
        search_text.setTypeface(medium);
        bagian_terbaru.setTypeface(semibold);
        the_magic.setTypeface(semibold);
        progress_buku_1.setTypeface(medium);
        the_martian.setTypeface(semibold);
        progress_buku_2.setTypeface(medium);
        judul_buku_3s.setTypeface(semibold);
        progress_buku_3.setTypeface(medium);
        filter_semua_buku.setTypeface(semibold);
        filter_komik.setTypeface(semibold);
        filter_novel.setTypeface(semibold);
        filter_fantasi.setTypeface(semibold);
        filter_dongeng.setTypeface(semibold);
        bagian_trending.setTypeface(semibold);
        penulis_buku_trend_1.setTypeface(medium);
        judul_buku_trend_1.setTypeface(semibold);
        penulis_buku_trend_2.setTypeface(medium);
        judul_buku_trend_2.setTypeface(semibold);
        penulis_buku_trend_3.setTypeface(medium);
        judul_buku_trend_3.setTypeface(semibold);
        daftar_buku.setTypeface(semibold);

        return h;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data untuk recyclerView
        mData = new ArrayList<>();
        mData.add(new Book(R.drawable.trending_book1, "Buku #1", "Ini adalah buku #1"));
        mData.add(new Book(R.drawable.trending_book2, "Buku #2", "Ini adalah buku #2"));
        mData.add(new Book(R.drawable.trending_book3, "Buku #3", "Ini adalah buku #3"));
        mData.add(new Book(R.drawable.trending_book3, "Buku #4", "Ini adalah buku #4"));
        mData.add(new Book(R.drawable.trending_book2, "Buku #5", "Ini adalah buku #5"));
        mData.add(new Book(R.drawable.trending_book2, "Buku #6", "Ini adalah buku #6"));
        mData.add(new Book(R.drawable.trending_book1, "Buku #7", "Ini adalah buku #7"));

    }
}