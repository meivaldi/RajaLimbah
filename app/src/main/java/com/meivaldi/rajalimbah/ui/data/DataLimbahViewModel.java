package com.meivaldi.rajalimbah.ui.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataLimbahViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DataLimbahViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Data Limbah fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
