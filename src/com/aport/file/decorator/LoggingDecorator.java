package com.aport.file.decorator;

import com.aport.common.Decorator;
import com.aport.common.command.Command;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

public class LoggingDecorator extends Decorator {
    private static final String LOG_FILE_PATH = "data/log.txt";

    public LoggingDecorator(Command command) {
        super(command);
    }

    @Override
    public Object execute() {
        log();
        return super.execute();
    }

    private void log() {
        Command cmd = this.decoratedCommand;
        while (cmd instanceof Decorator) {
            cmd = ((Decorator) cmd).getInnerCommand();
        }
        String commandName = cmd.getClass().getSimpleName();
        LocalDateTime timestamp = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(LOG_FILE_PATH, true))) {
            writer.write(String.format("[%s] %s 실행 완료", timestamp, commandName));
            writer.newLine();
        } catch (java.io.IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
            
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
