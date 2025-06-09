package com.aport.reservation.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.flight.decorator.ViewFlightNoticesDecorator;
import com.aport.user.decorator.ValidateDecorator;

public class ModifyReservationCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new ViewFlightNoticesDecorator(new ModifyReservationCommand()));
    }
}
