package com.aport.reservation.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.flight.decorator.ViewFlightsDecorator;
import com.aport.user.decorator.ValidateDecorator;

public class CreateReservationCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new ViewFlightsDecorator(new CreateReservationCommand()));
    }
}
