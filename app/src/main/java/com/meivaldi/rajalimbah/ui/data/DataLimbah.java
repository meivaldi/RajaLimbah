package com.meivaldi.rajalimbah.ui.data;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meivaldi.rajalimbah.R;
import com.meivaldi.rajalimbah.TambahDataLimbah;

public class DataLimbah extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataLimbahViewModel dataLimbahViewModel = ViewModelProviders.of(this).get(DataLimbahViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data_limbah, container, false);

        dataLimbahViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        FloatingActionButton tambahData = root.findViewById(R.id.add);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TambahDataLimbah.class));
            }
        });

        return root;
    }
}