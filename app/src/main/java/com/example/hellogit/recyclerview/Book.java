//Coding by Muhammad Rifqi Nugraha - Kelompok Coding
package com.example.hellogit.recyclerview;

import java.io.Serializable;

// Implement Serializable interface is important to send the book object to another activity
// Mengimplementasikan Interface Serializable sangat penting untuk mengirim objek buku ke activity lainnya
public class Book implements Serializable {

    int coverimg;
    String text;
    String descbook;

    public Book(int coverimg, String text, String descbook) {
        this.coverimg = coverimg;
        this.text = text;
        this.descbook = descbook;
    }

    public int getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(int coverimg) {
        this.coverimg = coverimg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescbook() {
        return descbook;
    }

    public void setDescbook(String descbook) {
        this.descbook = descbook;
    }
}