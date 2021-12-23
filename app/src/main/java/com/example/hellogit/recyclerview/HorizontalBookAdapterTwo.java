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

public class HorizontalBookAdapterTwo extends RecyclerView.Adapter {
    private final Context context;
    private final String Free = "GRATIS";
    BookAdapter.ItemClickListener mClickListener;
    Callback<Books> booksCallback;
    Callback<ImageLinks> ICallback;
    private ArrayList<Book> mData = new ArrayList<>();

    public HorizontalBookAdapterTwo(ArrayList<Book> mData, Context context, Callback<Books> booksCallback, BookAdapter.ItemClickListener mClickListener, Callback<ImageLinks> ICallback) {
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
        View view = layoutInflater.inflate(R.layout.item_view_horizontal_two, parent, false);
        return new HorizontalBookViewHolderTwo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = mData.get(position);
        // bind HorizontalBookViewHolderTwo
        HorizontalBookViewHolderTwo horizontalBookViewHolderTwo = (HorizontalBookViewHolderTwo) holder;
        Glide.with(horizontalBookViewHolderTwo.itemView).load(book.getImage())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .fitCenter()
                .format(DecodeFormat.PREFER_RGB_565)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_foreground)
                .into(horizontalBookViewHolderTwo.cover_buku_d1);
        horizontalBookViewHolderTwo.judul_buku_d1.setText(book.getTitle());
        if (book.getTitle().length() < 30)
            horizontalBookViewHolderTwo.judul_buku_d1.append("...");
        String author = book.getAuthors();
        horizontalBookViewHolderTwo.penulis_buku_d1.setText(author);

        horizontalBookViewHolderTwo.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(mData.get(position));
                horizontalBookViewHolderTwo.judul_buku_d1.setText(book.getTitle());
                horizontalBookViewHolderTwo.penulis_buku_d1.setText(book.getAuthors());

            }
        });

        //Custom Font
        Typeface regular = Typeface.createFromAsset(context.getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(context.getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(context.getAssets(), "font/PoppinsMedium.ttf");

        horizontalBookViewHolderTwo.judul_buku_d1.setTypeface(semibold);
        horizontalBookViewHolderTwo.penulis_buku_d1.setTypeface(medium);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public interface ItemClickListener {
        void onItemClick(Book book);
    }

    public static final class HorizontalBookViewHolderTwo extends RecyclerView.ViewHolder {

        ImageView cover_buku_d1, gambar_detail_buku;
        TextView judul_buku_d1, penulis_buku_d1, judul_buku_db, pengarang_buku_db,
                informasi_harga_db, judul_deskripsi_db, deskripsi_buku_db, rating_buku_db,
                jumlah_rating_buku_db, jumlah_hal_buku_db, hal_buku_db, bahasa, bahasa_buku_db;
        RatingBar rating_buku_d1;

        public HorizontalBookViewHolderTwo(@NonNull View itemView) {
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