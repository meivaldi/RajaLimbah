package com.meivaldi.rajalimbah.model;

import com.google.gson.annotations.SerializedName;

public class Kabupaten {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    public Kabupaten(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
