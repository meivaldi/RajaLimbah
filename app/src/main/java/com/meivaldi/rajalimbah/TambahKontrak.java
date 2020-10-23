package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.meivaldi.rajalimbah.api.ApiClient;
import com.meivaldi.rajalimbah.api.ApiInterface;
import com.meivaldi.rajalimbah.model.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKontrak extends AppCompatActivity {

    private EditText perusahaanET, jenisET;
    private Spinner tipeSP;
    private Button tambah;

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

        perusahaanET = findViewById(R.id.perusahaanET);
        jenisET = findViewById(R.id.jenisET);
        tipeSP = findViewById(R.id.tipeSP);
        tambah = findViewById(R.id.tambah);

        List<String> pengelolaan = new ArrayList<String>();

        pengelolaan.add("INTERNAL");
        pengelolaan.add("EKSTERNAL");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, pengelolaan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipeSP.setAdapter(dataAdapter);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("akun", MODE_PRIVATE);
                int uid = preferences.getInt("uid", 0);

                String perusahaan = perusahaanET.getText().toString();
                String tipe = tipeSP.getSelectedItem().toString();
                String jenis = jenisET.getText().toString();
                String lampiran = "Tes Lampiran";

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ApiResponse> call = apiService.addContract(uid, perusahaan, tipe, jenis, lampiran);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        ApiResponse res = response.body();
                        if (!res.isStatus()) {
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();

                            Log.d("TES", res.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}