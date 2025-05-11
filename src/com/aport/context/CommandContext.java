package com.aport.context;

import com.aport.command.*;
import com.aport.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContext {

    private final Map<Integer, Command> guestCommands = new HashMap<>();
    private final Map<Integer, Command> customerCommands = new HashMap<>();
    private final Map<Integer, Command> officerCommands = new HashMap<>();

    public CommandContext() {
        initializeGuestCommands();
        initializeCustomerCommands();
        initializeOfficerCommands();
    }

    private void initializeGuestCommands() {
        guestCommands.put(1, new SignupCommand());
        guestCommands.put(2, new LoginCommand());
    }

    private void initializeCustomerCommands() {
        customerCommands.put(1, new ViewFlightsCommand());
        customerCommands.put(2, new CreateReservationCommand());
        customerCommands.put(3, new ViewReservationsCommand());
        customerCommands.put(4, new LogoutCommand());
    }

    private void initializeOfficerCommands() {
        officerCommands.put(1, new CreateFlightCommand());
        officerCommands.put(2, new ViewFlightCommand());
        officerCommands.put(3, new CancelFlightCommand());
        officerCommands.put(4, new ModifyFlightCommand());
        officerCommands.put(5, new LogoutCommand());
    }

    public Command getGuestCommand(int key) {
        return guestCommands.get(key);
    }

    public Command getCustomerCommand(int key) {
        return customerCommands.get(key);
    }

    public Command getOfficerCommand(int key) {
        return officerCommands.get(key);
    }

    public boolean isGuest() {
        return UserService.getInstance().getCurrentUser() == null;
    }
}
