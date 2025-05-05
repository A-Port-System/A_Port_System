package com.aport.service;

import com.aport.user.User;

public abstract class BaseService {
	protected boolean validateLogin(User user) {
		if(user == null) {
			System.out.println("로그인 후 이용 가능합니다.");
			return false;
		}
		return true;
	}

}
