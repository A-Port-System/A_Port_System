package com.aport.context;

import com.aport.command.*;
import com.aport.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandContext {
    private final UserService userService;
    private final ReservationService reservationService;
    private final FlightService flightService;

    private final Map<Integer, Command> guestCommands = new HashMap<>();
    private final Map<Integer, Command> userCommands = new HashMap<>();

    public CommandContext(UserService userService,
                          ReservationService reservationService, FlightService flightService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.flightService = flightService;

        initializeGuestCommands();
        initializeUserCommands();
    }

    private void initializeGuestCommands() {
        guestCommands.put(1, new SignupCommand(userService));
        guestCommands.put(2, new LoginCommand(userService));
    }

    private void initializeUserCommands() {
        userCommands.put(1, new ViewFlightsCommand(flightService));
        userCommands.put(2, new CreateReservationCommand(reservationService, userService));
        userCommands.put(3, new ViewReservationsCommand(reservationService, userService));
        userCommands.put(4, new LogoutCommand(userService));
    }

    public Command getGuestCommand(int key) {
        return guestCommands.get(key);
    }

    public Command getUserCommand(int key) {
        return userCommands.get(key);
    }

    public boolean isGuest() {
        return userService.getCurrentUser() == null;
    }
}
