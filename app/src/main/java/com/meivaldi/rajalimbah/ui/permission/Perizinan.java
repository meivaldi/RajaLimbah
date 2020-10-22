package com.meivaldi.rajalimbah.ui.permission;

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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meivaldi.rajalimbah.R;
import com.meivaldi.rajalimbah.TambahDataLimbah;
import com.meivaldi.rajalimbah.TambahPerizinan;

public class Perizinan extends Fragment {

    private PerizinanViewModel perizinanViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perizinanViewModel =
                ViewModelProviders.of(this).get(PerizinanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perizinan, container, false);

        perizinanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        FloatingActionButton tambahData = root.findViewById(R.id.add_permission);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TambahPerizinan.class));
            }
        });

        return root;
    }
}