package com.B.week12.MVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;

public class DashboardDao implements IDashboardDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Account> getAllAccounts() {
		return jdbcTemplate.query("select * from account", new AccountUserMapper());
	}
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