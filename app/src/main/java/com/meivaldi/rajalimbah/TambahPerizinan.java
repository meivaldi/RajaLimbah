package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class TambahPerizinan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_perizinan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tambah Perizinan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}