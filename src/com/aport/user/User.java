package com.aport.user;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public abstract class User implements Serializable {
    protected String id;
    protected String password;
    protected String name;
    protected List<String> registeredCards = new ArrayList<>();
    protected int mileage = 0;

    public User(String username, String password, String name) {
        this.id = username;
        this.password = password;
        this.name = name;
    }

    public User() {
        // 기본 생성자: 직렬화된 데이터를 로드할 때 사용됩니다.
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