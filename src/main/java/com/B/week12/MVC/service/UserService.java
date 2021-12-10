package com.B.week12.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.B.week12.MVC.dao.IUserDao;
import com.B.week12.MVC.model.*;

public class UserService implements IUserService {

	  @Autowired
	  public IUserDao iUserDao;
	  @Override
	  public int register(Account account) {
	    return iUserDao.register(account);
	  }
	  @Override
	  public User validateUser(Login login) {
	    return iUserDao.validateUser(login);
	  }

	@Override
	public void createAccount(Account account) {
		// TODO Auto-generated method stub
		iUserDao.createAccount(account);
		
	}


	}
