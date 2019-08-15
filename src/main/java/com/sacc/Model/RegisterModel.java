package com.sacc.Model;


import com.mongodb.lang.NonNull;

/**
 * @author shilei
 */
public class RegisterModel {
    @NonNull
    private int phone;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String number;
    @NonNull
    private String major;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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

    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
}