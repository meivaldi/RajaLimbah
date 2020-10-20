package com.meivaldi.rajalimbah.ui.pengelolaan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meivaldi.rajalimbah.R;

public class Pengelolaan extends Fragment {

    private PengelolaanViewModel mViewModel;

    public static Pengelolaan newInstance() {
        return new Pengelolaan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pengelolaan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PengelolaanViewModel.class);
        // TODO: Use the ViewModel
    }

}