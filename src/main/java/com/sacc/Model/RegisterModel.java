package com.sacc.Model;


import com.mongodb.lang.NonNull;

/**
 * @author shilei
 */
public class RegisterModel {
    @NonNull
    private String studentNumber;
    @NonNull
    private String name;
    @NonNull
    private String email;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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

