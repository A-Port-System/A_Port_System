package com.aport.payment.command;

import com.aport.common.command.Command;
import com.aport.common.command.CommandFactory;
import com.aport.user.decorator.ValidateDecorator;

public class PaymentCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new ValidateDecorator(new PaymentCommand());
    }
}
