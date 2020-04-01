package com.example.navdrawerexample.ui.trainerList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class trainerListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public trainerListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}