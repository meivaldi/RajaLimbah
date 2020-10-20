package com.meivaldi.rajalimbah.ui.permission;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerizinanViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public PerizinanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Perizinan fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
