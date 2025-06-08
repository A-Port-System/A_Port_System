package com.aport.reservation.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.user.decorator.ValidateDecorator;

public class ViewReservationsCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new ViewReservationsCommand());
    }
}
