package com.example.hellogit.POJO;

import java.io.Serializable;

public class Book implements Serializable{

    private String Title;
    private String Authors;
    private String Image;
    private double Rating;
    private double Price;
    private Integer Pages;

    public Book(String judul, String pengarang, String cover, double peringkat, double harga, Integer halaman) {

        Title = judul;
        Authors = pengarang;
        Image = cover;
        Rating = peringkat;
        Price = harga;
        Pages = halaman;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String judul) {
        Title = judul;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String pengarang) {
        Authors = pengarang;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String cover) {
        Image = cover;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double peringkat) {
        Rating = peringkat;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double harga) {
        Price = harga;
    }

    public Integer getPages() {
        return Pages;
    }

    public void setPages(Integer halaman) {
        Pages = halaman;
    }
}