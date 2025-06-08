package com.aport.common.command;

import com.aport.user.decorator.ValidateDecorator;

public class UndoCommandFactory extends CommandFactory {

    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new UndoCommand());
    }
}
