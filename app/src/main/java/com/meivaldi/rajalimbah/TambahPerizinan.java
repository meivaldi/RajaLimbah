package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.meivaldi.rajalimbah.api.ApiClient;
import com.meivaldi.rajalimbah.api.ApiInterface;
import com.meivaldi.rajalimbah.model.ApiResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPerizinan extends AppCompatActivity {

    private Spinner jenisSp, satuanSp;
    private EditText noSurat, masaBerlaku, tanggalET;
    private DatePickerDialog.OnDateSetListener date;
    private Button tambah;

    final Calendar myCalendar = Calendar.getInstance();

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

        jenisSp = findViewById(R.id.jenisSP);
        List<String> jenisLimbah = new ArrayList<String>();

        jenisLimbah.add("Izin Lingkungan");
        jenisLimbah.add("Izin Pembuangan Air Limbah Ke Laut");
        jenisLimbah.add("Izin Pemanfaatan Limbah B3");
        jenisLimbah.add("Izin Pengolahan Limbah B3");
        jenisLimbah.add("Izin penimbunan Limbah B3");
        jenisLimbah.add("Izin Dumping Limbah B3 Ke Laut");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, jenisLimbah);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisSp.setAdapter(dataAdapter);

        satuanSp = findViewById(R.id.satuanSp);
        List<String> satuan = new ArrayList<String>();

        satuan.add("Hari");
        satuan.add("Bulan");
        satuan.add("Tahun");
        ArrayAdapter<String> satuanAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, satuan);
        satuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        satuanSp.setAdapter(satuanAdapter);

        noSurat = findViewById(R.id.no_surat);
        masaBerlaku = findViewById(R.id.masa_berlaku);
        tanggalET = findViewById(R.id.tanggalET);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        tanggalET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TambahPerizinan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tambah = findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("akun", MODE_PRIVATE);
                int uid = preferences.getInt("uid", 0);

                String jenis = jenisSp.getSelectedItem().toString();
                String nomor = noSurat.getText().toString();
                String tanggal = tanggalET.getText().toString();
                String masa = masaBerlaku.getText().toString() + " " + satuanSp.getSelectedItem().toString();
                String lampiran = "Tes";

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ApiResponse> call = apiService.addPermission(uid, jenis, nomor, tanggal, masa, lampiran);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        ApiResponse res = response.body();
                        if (!res.isStatus()) {
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggalET.setText(sdf.format(myCalendar.getTime()));
    }
}