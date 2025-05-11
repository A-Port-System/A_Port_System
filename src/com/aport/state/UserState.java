package com.aport.state;

import com.aport.command.Command;

public interface UserState {
    void initializeCommands();
    Command getCommand(int key);
    void handleMenu();
}
