package com.B.week12.MVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.B.week12.MVC.dao.AccountDao.AccountUserMapper;
import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;

public class TransationDao implements ITransationDao {
	private static final Logger LOGGER = Logger.getLogger(TransationDao.class);
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

	@Override
	public List<Account> getAccounts(int userId) {
		String sql = "select * from account where user_id='" + userId + "'";
		System.out.println("SQL:" + sql);
		List<Account> accountLst = jdbcTemplate.query(sql, new AccountUserMapper());

		return accountLst.size() > 0 ? accountLst : null;
	}
	

	class AccountUserMapper implements RowMapper<Account> {

		public Account mapRow(ResultSet rs, int arg1) throws SQLException {
			Account account = new Account();
			account.setAccountId(rs.getInt("account_id"));
			account.setAccountStatus(rs.getString("account_status"));
			account.setAccountType(rs.getString("account_Type"));
			account.setCurrentBalance(rs.getDouble("current_balance"));
			return account;

		}
	}


	@Override
	public void depositMoney(Transaction transaction) {
		// TODO Auto-generated method stub
		String sql = "update account set current_balance = (current_balance+?) where account_id=? ";
		int status = jdbcTemplate.update(sql, new Object[] {transaction.getTransactionAmount(), transaction.getFromAccount().getAccountId()});
		
		if (status != 0) {
			LOGGER.info("Deposit success");
		} else {
			LOGGER.info("Deposit failed");
		}
		
		sql = "insert into transaction(account_id, transaction_type, transaction_amount, comments, transaction_date) value(?,'deposit', ?, ?, SYSDATE())";
		status = jdbcTemplate.update(sql, new Object[] {transaction.getFromAccount().getAccountId(), transaction.getTransactionAmount(), transaction.getComments()});
		
		if (status != 0) {
			LOGGER.info("Transaction success");
		} else {
			LOGGER.info("Transaction failed");
		}
		
	}
	


}