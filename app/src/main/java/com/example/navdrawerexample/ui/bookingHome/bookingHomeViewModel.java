package com.example.navdrawerexample.ui.bookingHome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class bookingHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public bookingHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}