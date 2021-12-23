package com.example.hellogit.client;

import com.example.hellogit.POJO.Books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookClient {
    // Books
    @GET("volumes?q=maxResults=10")
    Call<Books> getBooks();

    // e-Books
    @GET("volumes?q=maxResults=10&filter=free-ebooks")
    Call<Books> getEBooks();

    // Buku Terbaru
    @GET("volumes?q=time&orderBy=newest")
    Call<Books> getNewBooks();

    @GET("volumes")
    Call<Books> searchVolumes(
            @Query("q") String query
    );
}