package com.meivaldi.rajalimbah.api;

import com.meivaldi.rajalimbah.model.ApiResponse;
import com.meivaldi.rajalimbah.model.CompanyResponse;
import com.meivaldi.rajalimbah.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("daftar_akun.php")
    Call<ApiResponse> registerUser(@Field("name") String name, @Field("email") String email, @Field("hp") String hp,
                                   @Field("jabatan") String jabatan, @Field("perusahaan") String perusahaan,
                                   @Field("hp_perusahaan") String hp_perusahaan, @Field("fax_perusahaan") String fax,
                                   @Field("kbli") String kbli, @Field("jenis_perusahaan") String jenis);

    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponse> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("company_info.php")
    Call<CompanyResponse> getCompanyInfo(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("save_permission.php")
    Call<ApiResponse> addPermission(@Field("uid") int uid, @Field("jenis") String jenis, @Field("nomor") String nomor,
                                    @Field("tanggal") String tanggal, @Field("masa") String masa, @Field("lampiran") String lampiran);

    @FormUrlEncoded
    @POST("save_contract.php")
    Call<ApiResponse> addContract(@Field("uid") int uid, @Field("perusahaan") String perusahaan, @Field("tipe") String tipe,
                                  @Field("jenis") String jenisLimbah, @Field("lampiran") String lampiran);

    @FormUrlEncoded
    @POST("simpan_limbah.php")
    Call<ApiResponse> addWaste(@Field("uid") int uid, @Field("kode") String kode, @Field("tanggal") String tanggal, @Field("masa") String masa,
                               @Field("tps") String tps, @Field("sumber") String sumber, @Field("jumlah") String jumlah, @Field("catatan") String catatan);
}
