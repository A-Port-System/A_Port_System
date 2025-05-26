package com.aport.state;

import com.aport.app.InputUtil;
import com.aport.command.Command;
import com.aport.service.UserService;
import com.aport.user.Customer;
import com.aport.user.Officer;
import com.aport.user.User;

import java.util.*;

public abstract class AbstractUserState implements UserState {
	protected final Map<Integer, Command> commands = new HashMap<>();
	
	public AbstractUserState() {
		initializeCommands();
	}

	
	@Override
	public final void handleMenu() {
		displayMenu();
		System.out.print("선택: ");
		int choice = InputUtil.readInt();
		
		if (isExitChoice(choice)) {
			handleExit();
			return;
		}
		
		Command command = getCommand(choice);
		if (command != null) {
			command.execute();
		} else {
			System.out.println("잘못된 입력입니다.");
		}
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
	
	protected abstract void displayMenu();
	protected abstract boolean isExitChoice(int choice);
	protected abstract void handleExit();
}
