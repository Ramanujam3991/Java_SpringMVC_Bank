package com.B.week12.MVC.dao;

import java.util.HashMap;
import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface ITransationDao {

int forexTransaction(Account account, double amount);

List<Account> getAccounts(int userId);

void depositMoney(Transaction transaction);
	

}
