package com.aport.flight.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;

public class SearchFlightsCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new SearchFlightsCommand();
    }
}
