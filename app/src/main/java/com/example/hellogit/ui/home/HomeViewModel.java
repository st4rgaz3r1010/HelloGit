package com.example.hellogit.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hellogit.POJO.Books;
import com.example.hellogit.client.BookClient;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {
    
    private static final String API_BASE = "https://www.googleapis.com/books/v1/";

    Interceptor interceptor;

    private BookClient bookClient;
    private MutableLiveData<Books> volumesResponseLiveData;
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ini adalah halaman beranda");

        volumesResponseLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();

        bookClient = new Retrofit.Builder()
                .baseUrl(API_BASE)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookClient.class);

    }

    public void searchVolumes(String keyword) {
        bookClient.searchVolumes(keyword)
                .enqueue(new Callback<Books>() {
                    @Override
                    public void onResponse(Call<Books> call, Response<Books> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Books> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Books> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

    public LiveData<String> getText() {
        return mText;
    }
}