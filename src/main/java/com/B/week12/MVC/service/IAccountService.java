package com.B.week12.MVC.service;

import java.util.ArrayList;
import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountService {

	Account getAccountDetails(Account account);

	List<Transaction> getTransactionDetails(Transaction transaction);


	double processForexTransaction(Account account, double amount, String toCurrency);
	
	double getConvertedAmount(Account account, double amount, String toCurrency);

	ArrayList<String> getAllCurrencies();

}
