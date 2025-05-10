package com.aport.command;

import com.aport.service.ReservationService;
import com.aport.service.UserService;

public class ViewReservationsCommand implements Command {
    private final ReservationService reservationService;
    private final UserService userService;

    public ViewReservationsCommand(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @Override
    public void execute() {
        reservationService.viewReservations(userService.getCurrentUser());
    }
}
