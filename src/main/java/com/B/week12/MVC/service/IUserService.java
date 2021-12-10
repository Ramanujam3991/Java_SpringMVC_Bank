package com.B.week12.MVC.service;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;

public interface IUserService {

	int register(Account account);

	User validateUser(Login login);

	void createAccount(Account account);
}