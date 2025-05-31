package com.aport.common;

import com.aport.user.domain.User;

public interface UserState {
    void initializeCommands();
    Command getCommand(int key);
    void handleMenu();
    void changeState(User user);
}
