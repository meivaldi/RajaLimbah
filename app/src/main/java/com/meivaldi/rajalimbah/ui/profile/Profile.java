package com.meivaldi.rajalimbah.ui.profile;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.meivaldi.rajalimbah.R;
import com.meivaldi.rajalimbah.api.ApiClient;
import com.meivaldi.rajalimbah.api.ApiInterface;
import com.meivaldi.rajalimbah.model.CompanyResponse;
import com.meivaldi.rajalimbah.model.Kabupaten;
import com.meivaldi.rajalimbah.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment {

    private ProfileViewModel profileViewModel;
    private ProgressDialog pDialog;

    private EditText nameET, typeET, kbliET, alamatET, telpET, faxET;
    private Spinner kabupatenSP;
    private List<String> kabupaten;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        nameET = root.findViewById(R.id.nameET);
        typeET = root.findViewById(R.id.typeET);
        kbliET = root.findViewById(R.id.kbliET);
        alamatET = root.findViewById(R.id.alamatET);
        telpET = root.findViewById(R.id.telpET);
        faxET = root.findViewById(R.id.faxET);

        kabupatenSP = root.findViewById(R.id.kabSP);
        kabupaten = new ArrayList<String>();

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Memuat...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences preferences = getActivity().getSharedPreferences("akun", MODE_PRIVATE);
        int uid = preferences.getInt("uid", 0);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CompanyResponse> call = apiService.getCompanyInfo(uid);

        call.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                CompanyResponse data = response.body();

                if (!data.isStatus()) {
                    String name = data.getCompanyName();
                    String type = data.getCompanyType();
                    String kbli = data.getKbli();
                    String alamat = data.getAlamat();
                    String telp = data.getTelp();
                    String fax = data.getFax();
                    List<Kabupaten> allKabupaten = data.getKabupatenList();

                    nameET.setText(name);
                    typeET.setText(type);
                    kbliET.setText(kbli);
                    alamatET.setText(alamat);
                    telpET.setText(telp);
                    faxET.setText(fax);

                    for (Kabupaten kb: allKabupaten) {
                        kabupaten.add(kb.getNama());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, kabupaten);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    kabupatenSP.setAdapter(dataAdapter);
                } else {
                    Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                }

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal memuat data!", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });

        return root;
    }
}