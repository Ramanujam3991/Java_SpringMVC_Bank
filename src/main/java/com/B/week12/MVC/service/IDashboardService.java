package com.B.week12.MVC.service;

import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;

public interface IDashboardService {

	List<Account> getAllAccounts();
}