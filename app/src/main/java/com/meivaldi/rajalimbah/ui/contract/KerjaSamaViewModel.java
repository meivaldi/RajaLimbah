package com.meivaldi.rajalimbah.ui.contract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KerjaSamaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public KerjaSamaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Kerja Sama fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
