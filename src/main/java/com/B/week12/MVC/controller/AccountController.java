package com.B.week12.MVC.controller;

import java.util.HashMap;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.IAccountService;
import com.B.week12.MVC.service.IForexService;
import com.B.week12.MVC.service.ISpringSessionValidator;



@Controller
public class AccountController {
	private static final Logger LOGGER = Logger.getLogger(AccountController.class);

	@Autowired
	IAccountService iAccountService;
	
	 @Autowired
	 IForexService iForexService;

	@RequestMapping(value = "/accountDetails/{accountType}", method = RequestMethod.GET)
	public ModelAndView viewAccount(@PathVariable String accountType, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if(!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");

		ModelAndView mav = new ModelAndView("viewaccount");
		LOGGER.info("Fetching Account Details in account controller and viewing:" + accountType);
		User user = (User) session.getAttribute("userObject");
		Account account = new Account();
		account.setUser(user);
		account.setAccountType(accountType);
		Account fromAccount = iAccountService.getAccountDetails(account);
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		Transaction recentTransaction = iAccountService.getTransactionDetails(transaction);
		recentTransaction.setFromAccount(fromAccount);
		LOGGER.info("account:" + account);
		LOGGER.info("transaction:" + recentTransaction);
		// mav.addObject("account", account);
		mav.addObject("transaction", recentTransaction);

		return mav;
	}
	
	 @RequestMapping(value = "/forex", method = RequestMethod.GET)
	 public ModelAndView showForex(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   ModelAndView mav = new ModelAndView("forex");  
	   User user = (User) session.getAttribute("userObject");
	   System.out.println("forex method | " + user.getUserId());
	   return mav;
	 }
	 
	 @RequestMapping(value = "/forexProcess", method = RequestMethod.POST)
	 public ModelAndView forexForm(HttpSession session,HttpServletRequest request, HttpServletResponse response) {   
	   ModelAndView mav = null;
	   User user = (User) session.getAttribute("userObject");
	   System.out.println("forexProcess method | " + user.getUserId());
	   HashMap dataMap = new HashMap();
	   dataMap.put("accountType", "checking");
	   dataMap.put("userId", user.getUserId());
	   Account account =  iAccountService.getAccountDetails(dataMap);
	   System.out.println(request.getParameter("from_currency"));
	   System.out.println(request.getParameter("to_currency"));
	   double test_from_currency = Double.parseDouble(request.getParameter("from_currency"));
	   String to_currency = request.getParameter("to_currency");
	   System.out.println("to currency is | "+to_currency);
	   double forex_amount=0;
	   
	   if(test_from_currency > account.getCurrentBalance()) {
		   System.out.println("Insufficient account balance");
		   mav = new ModelAndView("forex_error");
	   }
	   else {
		   //double amount = account.getCurrentBalance() - test_from_currency;
		   //double account_balance = account.getCurrentBalance();
		   iAccountService.processForexTransaction(account,test_from_currency,to_currency);
		   iForexService.forexTransaction(account,test_from_currency);
		   System.out.println(test_from_currency);
		   mav = new ModelAndView("forex_success");
		   mav.addObject("forex_amount", forex_amount);
	   }   
	   return mav;
	 }
}
