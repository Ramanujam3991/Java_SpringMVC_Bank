package com.B.week12.MVC.dao;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;

public interface IUserDao {

	int register(Account account);

	User validateUser(Login login);

	void createAccount(Account account);
}
