package com.aport.state;

import com.aport.app.InputUtil;
import com.aport.command.Command;
import com.aport.command.ViewFlightsCommand;
import com.aport.command.CreateReservationCommand;
import com.aport.command.ViewReservationsCommand;
import com.aport.service.UserService;
import com.aport.user.Customer;
import com.aport.user.Officer;
import com.aport.user.User;
import com.aport.command.LogoutCommand;
import com.aport.command.PaymentCommand;

import java.util.HashMap;
import java.util.Map;

public class CustomerState implements UserState {
    private final Map<Integer, Command> customerCommands = new HashMap<>();

    public CustomerState() {
        initializeCommands();
    }

    @Override
    public void initializeCommands() {
        customerCommands.put(1, new ViewFlightsCommand());
        customerCommands.put(2, new CreateReservationCommand());
        customerCommands.put(3, new ViewReservationsCommand());
        customerCommands.put(4, new PaymentCommand());
        customerCommands.put(5, new LogoutCommand());
    }

    @Override
    public Command getCommand(int key) {
        return customerCommands.get(key);
    }

    @Override
    public void handleMenu() {
        System.out.println("1. 항공편 검색/선택");
        System.out.println("2. 예약하기");
        System.out.println("3. 내 예약 조회");
        System.out.println("4. 티켓 발권");
        System.out.println("5. 로그아웃");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();

        Command command = getCommand(choice);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    @Override
    public void changeState(User user) {
        if (user instanceof Officer) {
            UserService.getInstance().setState(new OfficerState());
        } else if (user instanceof Customer) {
            UserService.getInstance().setState(new CustomerState());
        } else {
            UserService.getInstance().setState(new GuestState());
        }
    }
}