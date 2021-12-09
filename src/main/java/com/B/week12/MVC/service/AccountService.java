package com.B.week12.MVC.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.B.week12.MVC.dao.IAccountDao;
import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;

//import library classes curency exchange rate
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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


public class AccountService implements IAccountService{
	@Autowired
	  public IAccountDao iAccountDao;
	
	//currency rate api constants
	public static final String ACCESS_KEY = "1c1f282c994eca31a3762c6c2839d3a3";
	public static final String BASE_URL = "http://api.currencylayer.com/";
	public static final String ENDPOINT = "live";
	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public Account getAccountDetails(Account account) {
		// TODO Auto-generated method stub
		return iAccountDao.getAccountDetails(account);
	}

	public Transaction getTransactionDetails(Transaction transaction) {
		// TODO Auto-generated method stub
		return iAccountDao.getTransactionDetails(transaction);
	}
	
	
	//currency exchange rate method
	@Override
	public void processForexTransaction(Account account, double amount, String toCurrency) {
		// TODO Auto-generated method stub
		
		System.out.println("I am inside getConvertedRate of TransactionService class");
		HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&from=CAD&toINR&amount=10");
		double converted_amount = 0;
		double update_account_bal = account.getCurrentBalance() - amount;
	     try {
	         CloseableHttpResponse response =  httpClient.execute(get);
	         HttpEntity entity = response.getEntity();

	         // the following line converts the JSON Response to an equivalent Java Object
	         JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

	         System.out.println("Live Currency Exchange Rates");	         
	         converted_amount = exchangeRates.getJSONObject("quotes").getDouble(toCurrency);
	         System.out.println("From amount: "+amount+" | converted amount: "+converted_amount);
	         converted_amount *= amount;
	         System.out.println("The converted amount is "+converted_amount);
	         response.close();
	         //httpClient.close();	         
	     } catch (ClientProtocolException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     } catch (JSONException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     }	     		
		iAccountDao.processForexTransaction(account,update_account_bal);
	}
}
