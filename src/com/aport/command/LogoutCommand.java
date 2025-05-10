package com.aport.command;

import com.aport.service.UserService;

public class LogoutCommand implements Command {
    private final UserService userService;

    public LogoutCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.logout();
    }
}