package com.B.week12.MVC.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.B.week12.MVC.dao.ITransationDao;
import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

public class TransactionService implements ITransactionService{
	
	@Autowired
	  public ITransationDao iTransactionDao;
 	
	
	public int forexTransaction(Account account, double amount) {
		// TODO Auto-generated method stub
		return iTransactionDao.forexTransaction(account, amount);
	}


	@Override
	public List<Account> getAccounts(int userId) {
		// TODO Auto-generated method stub
		return iTransactionDao.getAccounts(userId);
	}


	@Override
	public void depositMoney(Transaction transaction) {
		// TODO Auto-generated method stub
		iTransactionDao.depositMoney(transaction);
	}
}
