package com.meivaldi.rajalimbah.ui.contract;

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

public class KerjaSama extends Fragment {

    private KerjaSamaViewModel kerjaSamaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kerjaSamaViewModel =
                ViewModelProviders.of(this).get(KerjaSamaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kerja_sama, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        kerjaSamaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}