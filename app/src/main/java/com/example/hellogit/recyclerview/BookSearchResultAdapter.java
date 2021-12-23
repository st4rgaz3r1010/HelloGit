package com.example.hellogit.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.hellogit.POJO.Item;
import com.example.hellogit.R;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultAdapter extends RecyclerView.Adapter<BookSearchResultAdapter.BSResultHolder> {
    Context context;
    private List<Item> results = new ArrayList<>();

    public BookSearchResultAdapter(List<Item> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public BSResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_items, parent, false);
        return new BSResultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BSResultHolder holder, int position) {
        Item item = results.get(position);

        holder.title_item.setText(item.getVolumeInfo().getTitle());
        holder.publishedDate_item.setText(item.getVolumeInfo().getPublishedDate());

        if (item.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .fitCenter()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.smallThumbnail_item);
        }

        String author = String.valueOf(item.getVolumeInfo().getAuthors());
        holder.authors_item.setText(author);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Item> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public static final class BSResultHolder extends RecyclerView.ViewHolder{

        ImageView smallThumbnail_item;
        TextView title_item, authors_item, publishedDate_item;

        public BSResultHolder(@NonNull View itemView) {
            super(itemView);

            smallThumbnail_item = itemView.findViewById(R.id.book_item_smallThumbnail);
            title_item = itemView.findViewById(R.id.book_item_title);
            authors_item = itemView.findViewById(R.id.book_item_authors);
            publishedDate_item = itemView.findViewById(R.id.book_item_publishedDate);
        }
    }
}