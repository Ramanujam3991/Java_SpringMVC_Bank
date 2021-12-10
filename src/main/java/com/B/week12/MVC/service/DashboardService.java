package com.B.week12.MVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.B.week12.MVC.dao.IDashboardDao;
import com.B.week12.MVC.dao.IUserDao;
import com.B.week12.MVC.model.*;

public class DashboardService implements IDashboardService {

	  @Autowired
	  public IDashboardDao iDashboardDao;

		public List<Account> getAllAccounts() {
			// TODO Auto-generated method stub
			return iDashboardDao.getAllAccounts();
		}


	}
