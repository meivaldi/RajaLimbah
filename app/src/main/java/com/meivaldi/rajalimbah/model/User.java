package com.meivaldi.rajalimbah.model;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("hp")
    private String phone;

    @SerializedName("jabatan")
    private String title;

    @SerializedName("perusahaan")
    private String company;

    @SerializedName("hp_perusahaan")
    private String companyPhone;

    @SerializedName("fax_perusahaan")
    private String companyFax;

    @SerializedName("kbli")
    private String kbli;

    @SerializedName("jenis_perusahaan")
    private String companyType;

    public User(){}

    public User(String name, String email, String phone, String title, String company,
                String companyPhone, String companyFax, String kbli, String companyType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.company = company;
        this.companyPhone = companyPhone;
        this.companyFax = companyFax;
        this.kbli = kbli;
        this.companyType = companyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getKbli() {
        return kbli;
    }

    public void setKbli(String kbli) {
        this.kbli = kbli;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
