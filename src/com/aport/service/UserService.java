package com.aport.service;

import com.aport.user.*;
import com.aport.strategy.signup.SignupStrategy;
import com.aport.state.*;

import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService {
    private static UserService instance;
    private final Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;
    private SignupStrategy signupStrategy;
    private UserState state;

    private UserService() {
        state = new GuestState();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public boolean validateLogin(User user) {
        return super.validateLogin(user);
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        state.changeState(user);
        this.currentUser = user;
    }

    public boolean isUser(String id, String password) {
        User user = userMap.get(id);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    public User getUser(String id) {
        return userMap.get(id);
    }

    public void setSignupStrategy(SignupStrategy signupStrategy) {
        this.signupStrategy = signupStrategy;
    }

    public void createUser() {
        if (signupStrategy != null) {
            signupStrategy.signUp();
        } else {
            System.out.println("Signup 전략이 설정되지 않았습니다.");
        }
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public UserState getState() {
        return state;
    }
}
