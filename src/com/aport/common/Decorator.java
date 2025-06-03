package com.aport.common;

import com.aport.common.command.Command;

public abstract class Decorator implements Command {
    protected Command decoratedCommand;

    public Decorator(Command command) {
        this.decoratedCommand = command;
    }

    public Command getInnerCommand() {
        return decoratedCommand;
    }

    public Object execute() {
        return decoratedCommand.execute();
    }
}