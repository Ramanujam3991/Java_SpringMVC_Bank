package com.B.week12.MVC.service;


import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountService {

	Account getAccountDetails(Account account);

	List<Transaction> getTransactionDetails(Transaction transaction);
	
	void processForexTransaction(Account account, double amount, String toCurrency);

}
