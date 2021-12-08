package com.B.week12.MVC.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.B.week12.MVC.dao.IAccountDao;
import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;


public class AccountService implements IAccountService{
	@Autowired
	  public IAccountDao iAccountDao;

	public Account getAccountDetails(HashMap<String, String> dataMap) {
		// TODO Auto-generated method stub
		return iAccountDao.getAccountDetails(dataMap);
	}

	public Transaction getTransactionDetails(HashMap<String, String> dataMap) {
		// TODO Auto-generated method stub
		return iAccountDao.getTransactionDetails(dataMap);
	}
}
