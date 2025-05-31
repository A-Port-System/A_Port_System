package com.aport.common.decorator;

import com.aport.common.command.Command;

public abstract class Decorator implements Command {
    protected Command decoratedCommand;

    public Decorator(Command command) {
        this.decoratedCommand = command;
    }

    public Object execute() {
        return decoratedCommand.execute();
    }
}