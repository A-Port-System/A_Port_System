package com.aport.state;

import com.aport.command.Command;
import com.aport.user.User;

public interface UserState {
    void initializeCommands();
    Command getCommand(int key);
    void handleMenu();
    void changeState(User user);
}
