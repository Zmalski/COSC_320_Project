package com.example.navdrawerexample.ui.myBookings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBookingsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyBookingsViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}
