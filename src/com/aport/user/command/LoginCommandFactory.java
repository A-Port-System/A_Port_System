package com.aport.user.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;

public class LoginCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new LoginCommand();
    }
}
