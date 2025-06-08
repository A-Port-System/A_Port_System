package com.aport.user.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.user.decorator.ValidateDecorator;

public class LogoutCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new LogoutCommand());
    }
}
