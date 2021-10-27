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

    public BookAdapter(List<Book> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_view, parent, false);

        return new bookviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookviewholder holder, int position) {

        holder.cover_buku_d1.setImageResource(mData.get(position).getCoverimg());
        holder.judul_buku_d1.setText(mData.get(position).getText());
        holder.penulis_buku_d1.setText(mData.get(position).getDescbook());

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

    public class bookviewholder extends RecyclerView.ViewHolder {

        ImageView cover_buku_d1;
        TextView judul_buku_d1, penulis_buku_d1;

        public bookviewholder(@NonNull View itemView) {
            super(itemView);

            cover_buku_d1 = itemView.findViewById(R.id.cover_buku_d1);
            judul_buku_d1 = itemView.findViewById(R.id.judul_buku_d1);
            penulis_buku_d1 = itemView.findViewById(R.id.penulis_buku_d1);
        }
    }
}