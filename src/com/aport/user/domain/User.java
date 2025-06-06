package com.aport.user.domain;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public abstract class User implements Serializable {
    protected String id;
    protected String password;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected UserType userType;

    protected List<String> registeredCards = new ArrayList<>();
    protected int mileage = 0;

    protected User(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        
    }
    
    public static class Builder {
        private String id;
        private String password;
        private String name;
        private String email;
        private String phoneNumber;
        private UserType userType;

        // Agency 전용
        protected String agencyName;
        
        // Officer 전용
        protected String department;
        
        public Builder userType(UserType userType) { this.userType = userType; return this; }
        public Builder id(String id) { this.id = id; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }

        public Builder agencyName(String agencyName) { this.agencyName = agencyName; return this; }

        public Builder department(String department) { this.department = department; return this; }

        public User build() {
            switch (userType) {
                case CUSTOMER:
                    return Customer.of(this);
                case AGENCY:
                    return Agency.of(this);
                case OFFICER:
                    return Officer.of(this);
                default:
                    throw new IllegalArgumentException("Invalid user type");
            }
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getRegisteredCards() { return registeredCards; }
    public void addCard(String cardNumber) { registeredCards.add(cardNumber); }
    public void setRegisteredCards(List<String> registeredCards) { this.registeredCards = registeredCards; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public void removeCard(String cardNumber) { registeredCards.remove(cardNumber);}
}