package com.aport.common.command;

import com.aport.common.Invoker;

public class UndoCommand implements Command {
    
    @Override
    public Object execute() {
        Invoker invoker = Invoker.getInstance();
        invoker.undo();
        return null;
    }
}
