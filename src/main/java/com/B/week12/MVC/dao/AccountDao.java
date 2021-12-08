package com.B.week12.MVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;

public class AccountDao implements IAccountDao {
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Account getAccountDetails(Account account) {
		String sql = "select * from account where user_id='" + account.getUser().getUserId() + "' and account_type='"
				+ account.getAccountType() + "'";
		System.out.println("SQL:" + sql);
		List<Account> fromAccounts = jdbcTemplate.query(sql, new AccountUserMapper());

		return fromAccounts.size() > 0 ? fromAccounts.get(0) : null;
	}
	public Transaction getTransactionDetails(Transaction transaction) {
		// TODO Auto-generated method stub
		String sql = "select * from transaction where account_id='" + transaction.getFromAccount().getAccountId() + "' ";
		System.out.println("SQL:" + sql);
		List<Transaction> recentTransactions = jdbcTemplate.query(sql, new TransactionUserMapper());

		return recentTransactions.size() > 0 ? recentTransactions.get(0) : null;
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
	
	class TransactionUserMapper implements RowMapper<Transaction> {

		public Transaction mapRow(ResultSet rs, int arg1) throws SQLException {
			Transaction transaction = new Transaction();
			transaction.setTransactionId(rs.getInt("transaction_id"));
			Account fromAccount = new Account();
			Account toAccount = new Account();
			fromAccount.setAccountId(rs.getInt("account_id"));
			toAccount.setAccountId(rs.getInt("to_account_id"));
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setTransactionType(rs.getString("transaction_type"));
			transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
			transaction.setComments(rs.getString("comments"));
			transaction.setTransactionDate(rs.getString("transaction_date"));
			return transaction;
			

		}
	}


}