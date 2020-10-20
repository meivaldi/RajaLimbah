package com.meivaldi.rajalimbah.ui.pengelolaan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PengelolaanViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public PengelolaanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pengelolaan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}