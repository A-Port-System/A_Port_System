package com.aport.seat.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.reservation.decorator.ViewReservationsDecorator;
import com.aport.user.decorator.ValidateDecorator;

public class SeatCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new ViewReservationsDecorator(new SelectSeatCommand()));
    }
}
