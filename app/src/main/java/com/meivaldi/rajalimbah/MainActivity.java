package com.meivaldi.rajalimbah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meivaldi.rajalimbah.api.ApiClient;
import com.meivaldi.rajalimbah.api.ApiInterface;
import com.meivaldi.rajalimbah.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button register, login;
    private EditText usernameET, passwordET;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Proses...");
        pDialog.setCancelable(false);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        login = findViewById(R.id.login);
        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);

        SharedPreferences preferences = getSharedPreferences("akun", MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);

        if (isLogin) {
            startActivity(new Intent(getApplicationContext(), BerandaActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<UserResponse> call = apiService.loginUser(username, password);

                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            UserResponse res = response.body();
                            if (!res.isStatus()) {
                                SharedPreferences pref = getSharedPreferences("akun", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();

                                editor.putBoolean("isLogin", true);
                                editor.putInt("uid", res.getUid());
                                editor.putString("name", res.getName());
                                editor.putString("email", res.getEmail());

                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), BerandaActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            pDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
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