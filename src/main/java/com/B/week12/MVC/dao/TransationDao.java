package com.B.week12.MVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;

public class TransationDao implements ITransationDao {
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int forexTransaction(Account account, double amount) {
		// TODO Auto-generated method stub
		System.out.println("I am inside forexTransaction of TransationDao class ");
		String sql = "insert into transaction(account_id,transaction_type,transaction_amount, comments,transaction_date) values(?,?,?,?,?)";
		System.out.println("procesForexTransation method inside AccountDao | " + account.getAccountId());
		System.out.println(sql);
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		int status = jdbcTemplate.update(sql, account.getAccountId(),"checking",amount,"Forex transaction",date);
		if (status != 0) {
			System.out.println("Forex transaction success ");
		} else {
			System.out.println("Forex transaction failed ");
		}
		return status;
	}
	


}