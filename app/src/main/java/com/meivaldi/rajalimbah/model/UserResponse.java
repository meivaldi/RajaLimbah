package com.meivaldi.rajalimbah.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("uid")
    private int uid;

    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public UserResponse(int uid, boolean status, String message, String name, String email) {
        this.uid = uid;
        this.status = status;
        this.message = message;
        this.name = name;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
}
