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

public class UserDao implements IUserDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int register(Account account) {
		User user = account.getUser();
		System.out.println("Data:" + user);
		String sql = "insert into user(user_name,password,first_name, last_name,email,address,phone) values(?,?,?,?,?,?,?)";

		jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });

		sql = "insert into login(user_name, password) values(?,?)";

		 jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword() });
		
		sql = "insert into account(user_id, account_type) values((select user_id from user where user_name=?),?)";

		return jdbcTemplate.update(sql, new Object[] { user.getUsername(), account.getAccountType() });
	}

	public User validateUser(Login login) {
		String sql = "select * from user where user_name='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		System.out.println("SQL:" + sql);
		List<User> users = jdbcTemplate.query(sql, new UserMapper());

		return users.size() > 0 ? users.get(0) : null;
	}

}

class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("user_name"));
		user.setPassword(rs.getString("password"));
		user.setFirstname(rs.getString("first_name"));
		user.setLastname(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPhone(rs.getString("phone"));

		return user;
	}
}
