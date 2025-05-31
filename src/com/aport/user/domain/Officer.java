package com.aport.user.domain;

public class Officer extends User {
    private String department;
    private String position;
    private String officeNumber;
    private String phoneNumber;
    private String email;
    private String hireDate;
    private boolean isAdmin;

    private Officer(Builder builder) {
        super(builder.username, builder.password, builder.name);
        this.department = builder.department;
        this.position = builder.position;
        this.officeNumber = builder.officeNumber;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.hireDate = builder.hireDate;
        this.isAdmin = builder.isAdmin;
    }

    public static class Builder implements java.io.Serializable {
        private String username;
        private String password;
        private String name;
        private String department;
        private String position;
        private String officeNumber;
        private String phoneNumber;
        private String email;
        private String hireDate;
        private boolean isAdmin;

        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder department(String department) { this.department = department; return this; }
        public Builder position(String position) { this.position = position; return this; }
        public Builder officeNumber(String officeNumber) { this.officeNumber = officeNumber; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder hireDate(String hireDate) { this.hireDate = hireDate; return this; }
        public Builder isAdmin(boolean isAdmin) { this.isAdmin = isAdmin; return this; }
        public Officer build() { return new Officer(this); }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}