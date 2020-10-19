package com.meivaldi.rajalimbah.api;

import com.meivaldi.rajalimbah.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Field;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("daftar_akun.php")
    Call<ApiResponse> registerUser(@Field("name") String name, @Field("email") String email, @Field("hp") String hp,
                                   @Field("jabatan") String jabatan, @Field("perusahaan") String perusahaan,
                                   @Field("hp_perusahaan") String hp_perusahaan, @Field("fax_perusahaan") String fax,
                                   @Field("kbli") String kbli, @Field("jenis_perusahaan") String jenis);

}
