package com.B.week12.MVC.dao;


import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountDao {

	Account getAccountDetails(Account account);

<<<<<<< HEAD
	Transaction getTransactionDetails(Transaction transaction);

=======
	Transaction getTransactionDetails(HashMap<String, String> dataMap);
		
	void processForexTransaction(Account account, double amount);
>>>>>>> 4932685bf76aad0c3f5153f79a4bfafeb8729a22
}
