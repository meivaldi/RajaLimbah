package com.meivaldi.rajalimbah.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyResponse {

    @SerializedName("nama")
    private String companyName;

    @SerializedName("tipe")
    private String companyType;

    @SerializedName("kbli")
    private String kbli;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("kabupaten")
    private String kabupaten;

    @SerializedName("telp")
    private String telp;

    @SerializedName("fax")
    private String fax;

    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("list_kabupaten")
    private List<Kabupaten> kabupatenList;

    public CompanyResponse(String companyName, String companyType, String kbli, String alamat,
                           String kabupaten, String telp, String fax, boolean status, String message, List<Kabupaten> kabupatenList) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.kbli = kbli;
        this.alamat = alamat;
        this.kabupaten = kabupaten;
        this.telp = telp;
        this.fax = fax;
        this.status = status;
        this.message = message;
        this.kabupatenList = kabupatenList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getKbli() {
        return kbli;
    }

    public void setKbli(String kbli) {
        this.kbli = kbli;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Kabupaten> getKabupatenList() {
        return kabupatenList;
    }

    public void setKabupatenList(List<Kabupaten> kabupatenList) {
        this.kabupatenList = kabupatenList;
    }
}
