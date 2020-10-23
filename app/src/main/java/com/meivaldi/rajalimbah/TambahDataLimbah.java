package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class TambahDataLimbah extends AppCompatActivity {

    private EditText tanggalET, jumlahET, catatanET;
    private Spinner kodeSp, masaSp, tpsSp, sumberSp, satuanSp;
    private Button tambah;
    private DatePickerDialog.OnDateSetListener date;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_limbah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tambah Data Limbah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tanggalET = findViewById(R.id.tanggalET);
        jumlahET = findViewById(R.id.jumlah);
        catatanET = findViewById(R.id.catatan);

        kodeSp = findViewById(R.id.kode);
        masaSp = findViewById(R.id.masa_simpan);
        tpsSp = findViewById(R.id.tps);
        sumberSp = findViewById(R.id.sumber);
        satuanSp = findViewById(R.id.satuan);
        tambah = findViewById(R.id.tambah);

        List<String> masaList = new ArrayList<String>();

        masaList.add("90 Hari");
        masaList.add("180 Hari");
        masaList.add("365 Hari");

        ArrayAdapter<String> masaAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, masaList);
        masaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        masaSp.setAdapter(masaAdapter);

        List<String> tpsList = new ArrayList<String>();

        tpsList.add("MASUK KE TPS INTERNAL");
        tpsList.add("MASUK KE TPS EKSTERNAL");

        ArrayAdapter<String> tpsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, tpsList);
        tpsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tpsSp.setAdapter(tpsAdapter);

        List<String> sumberList = new ArrayList<String>();

        sumberList.add("INTERNAL");
        sumberList.add("EKSTERNAL");

        ArrayAdapter<String> sumberAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sumberList);
        sumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sumberSp.setAdapter(sumberAdapter);

        List<String> satuanList = new ArrayList<String>();

        satuanList.add("TON");
        satuanList.add("KUINTAL");

        ArrayAdapter<String> satuanAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, satuanList);
        satuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        satuanSp.setAdapter(satuanAdapter);

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
                new DatePickerDialog(TambahDataLimbah.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("akun", MODE_PRIVATE);
                int uid = preferences.getInt("uid", 0);
                String kode = "tes";
                String tanggal = tanggalET.getText().toString();
                String masa = masaSp.getSelectedItem().toString();
                String tps = tpsSp.getSelectedItem().toString();
                String sumber = sumberSp.getSelectedItem().toString();
                String jumlah = jumlahET.getText().toString() + " " + satuanSp.getSelectedItem().toString();
                String catatan = catatanET.getText().toString();

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ApiResponse> call = apiService.addWaste(uid, kode, tanggal, masa, tps, sumber, jumlah, catatan);

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