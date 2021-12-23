package com.example.hellogit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hellogit.POJO.Books;
import com.example.hellogit.ui.home.HomeViewModel;

public class BookSearchViewModel extends AndroidViewModel {
    private HomeViewModel homeViewModel;
    private LiveData<Books> volumesResponseLiveData;


    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        homeViewModel = new HomeViewModel();
        volumesResponseLiveData = homeViewModel.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword) {
        homeViewModel.searchVolumes(keyword);
    }

    public LiveData<Books> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
