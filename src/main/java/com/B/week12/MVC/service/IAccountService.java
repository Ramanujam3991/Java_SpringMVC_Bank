package com.B.week12.MVC.service;

import java.util.HashMap;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public interface IAccountService {

	Account getAccountDetails(HashMap<String, String> dataMap);

	Transaction getTransactionDetails(HashMap<String, String> dataMap);

}
