package com.aport.common.command;

public interface Undoable {
    void undo(Object result);
}