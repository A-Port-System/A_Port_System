package com.aport.common;

import java.util.Stack;
import com.aport.app.Pair;

import com.aport.common.command.*;

public class Invoker {
    private static Invoker instance;
    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }
    private Invoker() {
    }

    private Command command;
    private Stack<Pair<Command, Object>> commandHistory = new Stack<>();

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        if (command != null) {
            Object result = command.execute();
            commandHistory.push(new Pair<>((Command) command, result));
        } else {
            System.out.println("[오류] 실행할 명령이 설정되지 않았습니다.");
        }
    }

    public void undo() {
        if (commandHistory.isEmpty()) {
            System.out.println("[오류] 실행 기록이 없습니다. 되돌릴 수 없습니다.");
            return;
        }

        while(!commandHistory.isEmpty()) {
            Pair<Command, Object> pair = commandHistory.pop();
            Command cmd = pair.first;
            Object result = pair.second;
            if (cmd instanceof Undoable) {
                ((Undoable) cmd).undo(result);
                return;
            }
        }
        System.out.println("[오류] 되돌릴 수 있는 명령이 없습니다.");
        return;
    }
}
