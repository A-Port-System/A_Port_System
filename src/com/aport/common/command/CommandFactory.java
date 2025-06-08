package com.aport.common.command;

import com.aport.file.decorator.LoggingDecorator;

public abstract class CommandFactory {
    public final Command create(){
        return new LoggingDecorator(createCommand());
    }
    
    abstract protected Command createCommand();
}