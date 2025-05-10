package com.aport.user;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String ID;
    private String password;
    private String name;
    private List<String> registeredCards = new ArrayList<>();
    private int mileage = 0;

    public User(String username, String password, String name) {
        this.ID = username;
        this.password = password;
        this.name = name;
    }

    public String getID() { return ID; }
    public void setUID(String ID) { this.ID = ID; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getRegisteredCards() { return registeredCards; }
    public void addCard(String cardNumber) { registeredCards.add(cardNumber); }
    public void setRegisteredCards(List<String> registeredCards) { this.registeredCards = registeredCards; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }
}