package com.example.hellogit.recyclerview;

import android.widget.ImageView;
import android.widget.TextView;

public interface BookCallback {

    void onBookItemClick(int position,
                         ImageView gambar_detail_buku,
                         TextView judul_buku_db,
                         TextView pengarang_buku_db,
                         TextView informasi_harga_db,
                         TextView judul_deskripsi_db,
                         TextView deskripsi_buku_db,
                         TextView rating_buku_db,
                         TextView jumlah_rating_buku_db,
                         TextView jumlah_hal_buku_db,
                         TextView hal_buku_db,
                         TextView bahasa_buku,
                         TextView bahasa_buku_db);
}
