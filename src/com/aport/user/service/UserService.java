package com.aport.user.service;

import com.aport.user.domain.User;
import com.aport.user.state.GuestState;
import com.aport.user.state.UserState;
import com.aport.user.strategy.SignupStrategy;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserService implements Serializable {
    private static UserService instance;
    private final Map<String, User> userMap = new HashMap<>();
    private transient User currentUser = null;
    private transient SignupStrategy signupStrategy;
    private transient UserState state;

    private UserService() {
        state = new GuestState();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public static void setInstance(UserService userService) {
        instance = userService;
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

    public void addUser() {
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
        if (state == null) {
            state = new GuestState();
        }
        return state;
    }
}
