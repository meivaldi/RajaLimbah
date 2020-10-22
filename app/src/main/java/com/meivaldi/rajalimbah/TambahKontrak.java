package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class TambahKontrak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontrak);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tambah Kontrak Kerjasama");
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