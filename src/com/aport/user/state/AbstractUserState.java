package com.aport.user.state;

import com.aport.app.InputUtil;
import com.aport.common.Command;
import com.aport.common.Invoker;
import com.aport.common.UserState;
import com.aport.user.domain.Customer;
import com.aport.user.domain.Officer;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

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
		
		if (!commands.containsKey(choice)) {
			System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			handleMenu();
			return;
		}

		Invoker invoker = new Invoker();
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
	
	protected abstract void displayMenu();
	protected abstract boolean isExitChoice(int choice);
	protected abstract void handleExit();
}
