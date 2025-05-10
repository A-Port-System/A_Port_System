package com.aport.command;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("[오류] 실행할 명령이 설정되지 않았습니다.");
        }
    }
}
