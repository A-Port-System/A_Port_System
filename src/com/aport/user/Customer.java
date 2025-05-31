package com.aport.user;

import java.io.Serializable;

public class Customer extends User {

    private String email;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private String gender;
    private String nationality;
    private boolean isVip;

    private Customer(Builder builder) {
        super(builder.username, builder.password, builder.name);
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.birthDate = builder.birthDate;
        this.gender = builder.gender;
        this.nationality = builder.nationality;
        this.isVip = builder.isVip;
    }

    public static class Builder implements Serializable {
        private String username;
        private String password;
        private String name;
        private String email;
        private String phoneNumber;
        private String address;
        private String birthDate;
        private String gender;
        private String nationality;
        private boolean isVip;

        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder birthDate(String birthDate) { this.birthDate = birthDate; return this; }
        public Builder gender(String gender) { this.gender = gender; return this; }
        public Builder nationality(String nationality) { this.nationality = nationality; return this; }
        public Builder isVip(boolean isVip) { this.isVip = isVip; return this; }
        public Customer build() { return new Customer(this); }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }
}