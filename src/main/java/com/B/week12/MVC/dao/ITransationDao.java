package com.B.week12.MVC.dao;

import java.util.HashMap;
import java.util.List;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;

public interface ITransationDao {

int forexTransaction(Account account, double amount);

List<Account> getAccounts(int userId);

void depositMoney(Transaction transaction);

void withdrawMoney(Transaction transaction);

boolean checkAccountExists(int accountId);

void transferMoney(Transaction transaction);

User validateUsername(String username);

void registerBiller(User user, User billerUser);

List<Account> getPayeeLst(int userId);
	

}
