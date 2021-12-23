//Coding by Muhammad Rifqi Nugraha - Kelompok Coding
package com.example.hellogit.recyclerview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellogit.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookviewholder> {
    List<Book> mData;
    Context context;
    BookCallback callback;
    ItemClickListener clickListener;

    public BookAdapter(List<Book> mData, Context context, BookCallback callback, ItemClickListener clickListener) {
        this.mData = mData;
        this.context = context;
        this.callback = callback;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BookAdapter.bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_view, parent, false);

        return new bookviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.bookviewholder holder, int position) {
        holder.cover_buku_d1.setImageResource(mData.get(position).getCoverimg());
        holder.judul_buku_d1.setText(mData.get(position).getText());
        holder.penulis_buku_d1.setText(mData.get(position).getDescbook());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(mData.get(position));
//              callback.onBookItemClick(getAbsoluteAdapterPosition(),
//                        gambar_detail_buku,
//                        judul_buku_db,
//                        pengarang_buku_db,
//                        informasi_harga_db,
//                        judul_deskripsi_db,
//                        deskripsi_buku_db,
//                        rating_buku_db,
//                        jumlah_rating_buku_db,
//                        jumlah_hal_buku_db,
//                        hal_buku_db,
//                        bahasa,
//                        bahasa_buku_db);
            }
        });

        //Custom Font
        Typeface regular = Typeface.createFromAsset(context.getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(context.getAssets(), "font/PoppinsMedium.ttf");

        holder.judul_buku_d1.setTypeface(semibold);
        holder.penulis_buku_d1.setTypeface(medium);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface ItemClickListener {
        void onItemClick(Book book);
    }

    class bookviewholder extends RecyclerView.ViewHolder {
        ImageView cover_buku_d1, gambar_detail_buku;
        TextView judul_buku_d1, penulis_buku_d1, judul_buku_db, pengarang_buku_db,
                informasi_harga_db, judul_deskripsi_db, deskripsi_buku_db, rating_buku_db,
                jumlah_rating_buku_db, jumlah_hal_buku_db, hal_buku_db, bahasa, bahasa_buku_db;

        public bookviewholder(@NonNull View itemView) {
            super(itemView);

            cover_buku_d1 = itemView.findViewById(R.id.cover_buku_d1);
            judul_buku_d1 = itemView.findViewById(R.id.judul_buku_d1);
            penulis_buku_d1 = itemView.findViewById(R.id.penulis_buku_d1);

            gambar_detail_buku = itemView.findViewById(R.id.gambar_detail_buku);
            judul_buku_db = itemView.findViewById(R.id.judul_buku_db);
            pengarang_buku_db = itemView.findViewById(R.id.pengarang_buku_db);
            informasi_harga_db = itemView.findViewById(R.id.informasi_harga_db);
            judul_deskripsi_db = itemView.findViewById(R.id.judul_deskripsi_db);
            deskripsi_buku_db = itemView.findViewById(R.id.deskripsi_buku_db);
            rating_buku_db = itemView.findViewById(R.id.rating_buku_db);
            jumlah_rating_buku_db = itemView.findViewById(R.id.jumlah_rating_buku_db);
            jumlah_hal_buku_db = itemView.findViewById(R.id.jumlah_hal_buku_db);
            hal_buku_db = itemView.findViewById(R.id.hal_buku_db);
            bahasa = itemView.findViewById(R.id.bahasa);
            bahasa_buku_db = itemView.findViewById(R.id.bahasa_buku_db);
        }
    }
}