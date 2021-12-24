//Coding by Muhammad Rifqi Nugraha - Kelompok Coding
package com.example.hellogit.ui.home;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellogit.BookDetailsFragment;
import com.example.hellogit.BookSearchViewModel;
import com.example.hellogit.POJO.Book;
import com.example.hellogit.POJO.Books;
import com.example.hellogit.POJO.ImageLinks;
import com.example.hellogit.POJO.Item;
import com.example.hellogit.POJO.RetailPrice;
import com.example.hellogit.POJO.SaleInfo;
import com.example.hellogit.POJO.VolumeInfo;
import com.example.hellogit.R;
import com.example.hellogit.client.BookClient;
import com.example.hellogit.recyclerview.BookAdapter;
import com.example.hellogit.recyclerview.BookAdapter.ItemClickListener;
import com.example.hellogit.recyclerview.BookSearchResultAdapter;
import com.example.hellogit.recyclerview.HorizontalBookAdapter;
import com.example.hellogit.recyclerview.HorizontalBookAdapterTwo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class HomeFragment extends Fragment implements BookAdapter.ItemClickListener {
    private HomeViewModel homeViewModel;

    private static final String TAG = "fragment_home";
    Context context;
    ItemClickListener mClickListener;
    Callback<Books> Callback;
    Callback<ImageLinks> ICallback;
    TextView nama_profil, desc_profil, search_edit, bagian_terbaru, bagian_trending, daftar_buku;
    ImageButton search_btn;
    private RecyclerView recyclerview, recyclerview_horizontal, recyclerview_search,
            recyclerview_horizontal_two;
    private View h;
    private ArrayList<Book> mData = new ArrayList<>();
    private BookAdapter bookAdapter;
    private HorizontalBookAdapter horizontalBookAdapter;
    private HorizontalBookAdapterTwo horizontalBookAdapterTwo;
    private BookSearchResultAdapter bookSearchResultAdapter;
    private BookSearchViewModel bookSearchViewModel;
    private List<Item> results = new ArrayList<>();

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookSearchResultAdapter = new BookSearchResultAdapter(results, context);

        bookSearchViewModel = ViewModelProviders.of(this).get(BookSearchViewModel.class);
        bookSearchViewModel.init();
        bookSearchViewModel.getVolumesResponseLiveData().observe(this, new Observer<Books>() {
            @Override
            public void onChanged(Books books) {
                if (books != null) {
                    bookSearchResultAdapter.setResults(books.getItems());
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        h = inflater.inflate(R.layout.fragment_home, container, false);
        h.setTag(TAG);
        //Mendapatkan referensi untuk recyclerView
        recyclerview = h.findViewById(R.id.recyclerview);
        recyclerview_horizontal = h.findViewById(R.id.recyclerview_horizontal);
        recyclerview_search = h.findViewById(R.id.recyclerview_search);
        recyclerview_horizontal_two = h.findViewById(R.id.recyclerview_horizontal_two);
        //Mengatur atribut ukuran item lebih optimize
        recyclerview.setHasFixedSize(true);
        recyclerview_horizontal.setHasFixedSize(true);
        recyclerview_search.setHasFixedSize(true);
        recyclerview_horizontal_two.setHasFixedSize(true);
        //Mengatur layoutManager
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview_horizontal.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerview_horizontal.setItemAnimator(new DefaultItemAnimator());
        recyclerview_horizontal_two.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerview_horizontal_two.setItemAnimator(new DefaultItemAnimator());
        recyclerview_search.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerview_search.setItemAnimator(new DefaultItemAnimator());
        recyclerview_search.setAdapter(bookSearchResultAdapter);

        search_edit = h.findViewById(R.id.search_edit);
        search_btn = h.findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perform_search();
            }
        });

        // inisialisasi variabel
        nama_profil = (TextView) h.findViewById(R.id.nama_profil);
        desc_profil = (TextView) h.findViewById(R.id.desc_profil);
        search_edit = (TextView) h.findViewById(R.id.search_edit);
        bagian_terbaru = (TextView) h.findViewById(R.id.bagian_terbaru);
        bagian_trending = (TextView) h.findViewById(R.id.bagian_trending);
        daftar_buku = (TextView) h.findViewById(R.id.daftar_buku);

        // Custom Font
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsRegular.ttf");
        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsSemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsBold.ttf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "font/PoppinsMedium.ttf");

        nama_profil.setTypeface(semibold);
        desc_profil.setTypeface(regular);
        search_edit.setTypeface(medium);
        bagian_terbaru.setTypeface(semibold);
        bagian_trending.setTypeface(semibold);
        daftar_buku.setTypeface(semibold);

        network_data();

        return h;
    }

    public void perform_search() {
        String keyword = search_edit.getEditableText().toString();
        bookSearchViewModel.searchVolumes(keyword);
    }

    private void network_data() {
        String API_BASE = "https://www.googleapis.com/books/v1/";

        OkHttpClient.Builder httpsClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpsClient.build())
                .build();

        BookClient ebooksInterface = retrofit.create(BookClient.class);
        Call<Books> call = ebooksInterface.getBooks();
        call.enqueue(new Callback<Books>() {
            private Object Book;

            @Override
            public void onResponse(@NonNull Call<Books> call, @NonNull Response<Books> response) {
                if (response.isSuccessful()){
                    List<Item> itemList = response.body().getItems();
                    for (Item item : itemList){
                        VolumeInfo infoVolume = item.getVolumeInfo();
                        SaleInfo info_penjualan = item.getSaleInfo();
                        RetailPrice harga_eceran = info_penjualan.getRetailPrice();
                        String judul = infoVolume.getTitle();
                        List<String> daftar_pengarang = infoVolume.getAuthors();
                        String pengarang = "";
                        Integer halaman = 0;
                        if (daftar_pengarang.size() == 0) {
                            pengarang = "oleh Anonim";
                        }
                        else {
                            pengarang = "oleh " + daftar_pengarang.get(0);
                        }
                        ImageLinks imageLinks = infoVolume.getImageLinks();
                        String cover = imageLinks.getSmallThumbnail()
                                .replace("http://", "https://");
                        double peringkat = 0, harga = 0;
                        if (infoVolume.getAverageRating() != null){
                            peringkat = infoVolume.getAverageRating();
                        } else {
                            peringkat = 1;
                        }

                        if (harga_eceran.getAmount() != null) {
                            harga = harga_eceran.getAmount();
                        } else {
                            harga = 0.00;
                        }

                        int hitung = infoVolume.getPageCount();
                        if (hitung != 0){
                            halaman = infoVolume.getPageCount();
                        } else {
                            halaman = 0;
                        }
                        mData.add(new Book(judul, pengarang, cover, peringkat, harga, halaman));
                    }
                    // Membuat adapter
                    bookAdapter = new BookAdapter(mData, getContext(), Callback,
                            HomeFragment.this, ICallback);
                    horizontalBookAdapter = new HorizontalBookAdapter(mData, getContext(), Callback,
                            HomeFragment.this, ICallback);
                    horizontalBookAdapterTwo = new HorizontalBookAdapterTwo(mData, getContext(),
                            Callback, HomeFragment.this, ICallback);
                    // Mengatur adapter
                    recyclerview.setAdapter(bookAdapter);
                    recyclerview_horizontal.setAdapter(horizontalBookAdapter);
                    recyclerview_horizontal_two.setAdapter(horizontalBookAdapterTwo);
                } else {
                        Toast.makeText(getContext(), "Error : " + response.body(),
                                Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(com.example.hellogit.POJO.Book book) {
        Fragment title_book = BookDetailsFragment.newInstance(book.getTitle());

        // Buat fragmen dan berikan argumen untuk buku yang dipilih
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("fragment_home"));
        transaction.add(R.id.fragment_container, title_book);
        transaction.addToBackStack(null); // Tambahkan transaksi ke back-stack sehingga pengguna dapat menavigasi kembali
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit(); // Lakukan transaksi fragmen
    }
}