package com.B.week12.MVC.service;

import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface ITransactionService {
	public int forexTransaction(Account account, double amount);

	public List<Account> getAccounts(int userId);

	public void depositMoney(Transaction transaction);
}

