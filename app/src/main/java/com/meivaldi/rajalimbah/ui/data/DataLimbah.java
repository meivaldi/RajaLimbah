package com.meivaldi.rajalimbah.ui.data;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meivaldi.rajalimbah.R;

public class DataLimbah extends Fragment {

    private DataLimbahViewModel dataLimbahViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataLimbahViewModel =
                ViewModelProviders.of(this).get(DataLimbahViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data_limbah, container, false);

        dataLimbahViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}