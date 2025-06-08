package com.aport.user.command;

import com.aport.common.command.Command;

public class ExitCommand implements Command{
    @Override
    public Object execute() {
        System.out.println("시스템을 종료합니다...");
        System.exit(0);
        return null;
    }
}
