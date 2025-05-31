package com.aport.user;
import java.io.Serializable;

public class Agency extends User {
    private String agencyCode;
    private String agencyName;
    private String agencyType;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private String registrationNumber;
    private boolean isPartner;

    private Agency(Builder builder) {
        super(builder.username, builder.password, builder.name);
        this.agencyCode = builder.agencyCode;
        this.agencyName = builder.agencyName;
        this.agencyType = builder.agencyType;
        this.contactPerson = builder.contactPerson;
        this.contactPhone = builder.contactPhone;
        this.contactEmail = builder.contactEmail;
        this.address = builder.address;
        this.registrationNumber = builder.registrationNumber;
        this.isPartner = builder.isPartner;
    }

    public static class Builder implements Serializable {
        private String username;
        private String password;
        private String name;
        private String agencyCode;
        private String agencyName;
        private String agencyType;
        private String contactPerson;
        private String contactPhone;
        private String contactEmail;
        private String address;
        private String registrationNumber;
        private boolean isPartner;

        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder agencyCode(String agencyCode) { this.agencyCode = agencyCode; return this; }
        public Builder agencyName(String agencyName) { this.agencyName = agencyName; return this; }
        public Builder agencyType(String agencyType) { this.agencyType = agencyType; return this; }
        public Builder contactPerson(String contactPerson) { this.contactPerson = contactPerson; return this; }
        public Builder contactPhone(String contactPhone) { this.contactPhone = contactPhone; return this; }
        public Builder contactEmail(String contactEmail) { this.contactEmail = contactEmail; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder registrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; return this; }
        public Builder isPartner(boolean isPartner) { this.isPartner = isPartner; return this; }
        public Agency build() { return new Agency(this); }
    }

    public String getAgencyCode() { return agencyCode; }
    public void setAgencyCode(String agencyCode) { this.agencyCode = agencyCode; }
    public String getAgencyName() { return agencyName; }
    public void setAgencyName(String agencyName) { this.agencyName = agencyName; }
    public String getAgencyType() { return agencyType; }
    public void setAgencyType(String agencyType) { this.agencyType = agencyType; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public boolean isPartner() { return isPartner; }
    public void setPartner(boolean isPartner) { this.isPartner = isPartner; }
}