package com.B.week12.MVC.dao;

import java.util.HashMap;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountDao {

	Account getAccountDetails(HashMap<String, String> dataMap);

	Transaction getTransactionDetails(HashMap<String, String> dataMap);

}
