package com.aport.flight.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;

public class ViewFlightsCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ViewFlightsCommand();
    }
}
