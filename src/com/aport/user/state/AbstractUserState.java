package com.aport.user.state;

import com.aport.app.InputUtil;
import com.aport.common.Invoker;
import com.aport.common.command.Command;
import com.aport.user.domain.Customer;
import com.aport.user.domain.Officer;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;
import java.io.IOException;
import java.util.*;

public abstract class AbstractUserState implements UserState {
	protected final Map<Integer, Command> commands = new HashMap<>();
	
	public AbstractUserState() {
		initializeCommands();
	}

	
	@Override
	public final void handleMenu() {
		clearConsole();
		displayMenu();
		System.out.print("선택: ");
		int choice = InputUtil.readInt();
		
		if (!commands.containsKey(choice)) {
			System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			handleMenu();
			return;
		}

		Invoker invoker = Invoker.getInstance();
		invoker.setCommand(commands.get(choice));
		invoker.run();
	}
	
	@Override
	public Command getCommand(int key) {
		return commands.get(key);
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

	public static void clearConsole() {
		InputUtil.readLine("다음으로 계속하려면 Enter 키를 누르세요...");
        try {
        	String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	protected abstract void displayMenu();
}
