package com.B.week12.MVC.service;

import java.util.HashMap;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountService {

	Account getAccountDetails(Account account);

<<<<<<< HEAD
	Transaction getTransactionDetails(Transaction transaction);
=======
	Transaction getTransactionDetails(HashMap<String, String> dataMap);
	
	void processForexTransaction(Account account, double amount, String toCurrency);
>>>>>>> 4932685bf76aad0c3f5153f79a4bfafeb8729a22

}
