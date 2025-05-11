package com.aport.context;

import com.aport.command.*;
import com.aport.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContext {

    private final Map<Integer, Command> guestCommands = new HashMap<>();
    private final Map<Integer, Command> userCommands = new HashMap<>();


    public CommandContext() {
        initializeGuestCommands();
        initializeUserCommands();
    }

    private void initializeGuestCommands() {
        guestCommands.put(1, new SignupCommand());
        guestCommands.put(2, new LoginCommand());
    }

    private void initializeUserCommands() {
        userCommands.put(1, new ViewFlightsCommand());
        userCommands.put(2, new CreateReservationCommand());
        userCommands.put(3, new ViewReservationsCommand());
        userCommands.put(4, new LogoutCommand());
    }

    public Command getGuestCommand(int key) {
        return guestCommands.get(key);
    }

    public Command getUserCommand(int key) {
        return userCommands.get(key);
    }

    public boolean isGuest() {
        return UserService.getInstance().getCurrentUser() == null;
    }
}
