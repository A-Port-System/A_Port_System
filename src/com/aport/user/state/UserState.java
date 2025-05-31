package com.aport.user.state;

import com.aport.common.command.Command;
import com.aport.user.domain.User;

public interface UserState {
    void initializeCommands();
    Command getCommand(int key);
    void handleMenu();
    void changeState(User user);
}
