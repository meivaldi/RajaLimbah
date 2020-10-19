package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meivaldi.rajalimbah.api.ApiClient;
import com.meivaldi.rajalimbah.api.ApiInterface;
import com.meivaldi.rajalimbah.model.ApiResponse;
import com.meivaldi.rajalimbah.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameET, emailET, emailConfirmET, phoneET, titleET,
            companyET, companyPhoneET, companyFaxET, kbliET, companyTypeET;
    private TextView fileName;
    private Button download, upload, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        nameET = findViewById(R.id.name);
        emailET = findViewById(R.id.email);
        emailConfirmET = findViewById(R.id.email_confirm);
        phoneET = findViewById(R.id.phone);
        titleET = findViewById(R.id.title);

        companyET = findViewById(R.id.company_name);
        companyPhoneET = findViewById(R.id.company_phone);
        companyFaxET = findViewById(R.id.company_fax);
        kbliET = findViewById(R.id.kbli);
        companyTypeET = findViewById(R.id.jenis);

        fileName = findViewById(R.id.file);
        download = findViewById(R.id.download);
        upload = findViewById(R.id.upload);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String emailConfirm = emailConfirmET.getText().toString();
                String phone = phoneET.getText().toString();
                String title = titleET.getText().toString();

                String company = companyET.getText().toString();
                String companyPhone = companyPhoneET.getText().toString();
                String companyFax = companyFaxET.getText().toString();
                String kbli = kbliET.getText().toString();
                String companyType = companyTypeET.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (emailConfirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Konfirmasi Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Hp Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Jabatan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (company.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Perusahaan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (companyPhone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "HP Perusahaan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (companyFax.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nomor Fax Perusahaan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (kbli.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "KBLI Perusahaan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (companyType.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Jenis Perusahaan Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                } else if (!email.isEmpty() && !emailConfirm.isEmpty() && !email.equals(emailConfirm)) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Sama!", Toast.LENGTH_SHORT).show();
                } else {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<ApiResponse> call = apiService.registerUser(name,
                            email,
                            phone,
                            title,
                            company,
                            companyPhone,
                            companyFax,
                            kbli,
                            companyType);

                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            ApiResponse res = response.body();
                            if (!res.isStatus()) {
                                Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Registrasi Gagal!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}