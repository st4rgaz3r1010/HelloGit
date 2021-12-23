//Coding by Muhammad Rifqi Nugraha - Kelompok Coding
package com.example.hellogit.recyclerview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.hellogit.POJO.Book;
import com.example.hellogit.POJO.Books;
import com.example.hellogit.POJO.ImageLinks;
import com.example.hellogit.R;

import java.util.ArrayList;

import retrofit2.Callback;

public class BookAdapter extends RecyclerView.Adapter {
    public static final String ARG_PARAM1 = "param1";
    private final Context context;
    private final String Free = "GRATIS";
    ItemClickListener mClickListener;
    Callback<Books> booksCallback;
    Callback<ImageLinks> ICallback;
    private ArrayList<Book> mData = new ArrayList<>();

    public BookAdapter(ArrayList<Book> mData, Context context, Callback<Books> booksCallback, ItemClickListener mClickListener, Callback<ImageLinks> ICallback) {
        this.mData = mData;
        this.context = context;
        this.booksCallback = booksCallback;
        this.mClickListener = mClickListener;
        this.ICallback = ICallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_view, parent, false);;
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = mData.get(position);
            // bind BookViewHolder One
            BookViewHolder bookViewHolder = (BookViewHolder) holder;
            Glide.with(bookViewHolder.itemView)
                    .load(book.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_foreground)
                    .into(bookViewHolder.cover_buku_d1);
            bookViewHolder.judul_buku_d1.setText(book.getTitle());
            if (book.getTitle().length() < 30)
                bookViewHolder.judul_buku_d1.append("...");
            String author = book.getAuthors();
            bookViewHolder.penulis_buku_d1.setText(author);
            bookViewHolder.rating_buku_d1.setRating((float) book.getRating());
            if(book.getPrice() != 0.00)
                bookViewHolder.informasi_harga_db.setText(String.valueOf(book.getPrice()));
            else
                bookViewHolder.informasi_harga_db.setText(Free);
            String hal = book.getPages() +" Halaman";
            bookViewHolder.jumlah_hal_buku_db.setText(hal);

            bookViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(mData.get(position));
                    bookViewHolder.judul_buku_d1.setText(book.getTitle());
                    bookViewHolder.penulis_buku_d1.setText(book.getAuthors());
                    bookViewHolder.rating_buku_d1.setRating((float) book.getRating());
                }
            });

            // Custom Font
            Typeface regular = Typeface.createFromAsset(context.getAssets(), "font/PoppinsRegular.ttf");
            Typeface semibold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsSemiBold.ttf");
            Typeface bold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsBold.ttf");
            Typeface medium = Typeface.createFromAsset(context.getAssets(), "font/PoppinsMedium.ttf");

            bookViewHolder.judul_buku_d1.setTypeface(semibold);
            bookViewHolder.penulis_buku_d1.setTypeface(medium);
            bookViewHolder.informasi_harga_db.setTypeface(medium);
            bookViewHolder.jumlah_hal_buku_db.setTypeface(medium);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public interface ItemClickListener {
        void onItemClick(Book book);
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView cover_buku_d1, gambar_detail_buku;
        TextView judul_buku_d1, penulis_buku_d1, judul_buku_db, pengarang_buku_db,
                informasi_harga_db, judul_deskripsi_db, deskripsi_buku_db, rating_buku_db,
                jumlah_rating_buku_db, jumlah_hal_buku_db, hal_buku_db, bahasa, bahasa_buku_db;
        RatingBar rating_buku_d1;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            cover_buku_d1 = itemView.findViewById(R.id.cover_buku_d1);
            judul_buku_d1 = itemView.findViewById(R.id.judul_buku_d1);
            penulis_buku_d1 = itemView.findViewById(R.id.penulis_buku_d1);
            rating_buku_d1 = itemView.findViewById(R.id.rating_buku_d1);

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