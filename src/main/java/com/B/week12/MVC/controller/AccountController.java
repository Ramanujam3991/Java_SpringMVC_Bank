package com.B.week12.MVC.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.IAccountService;
import com.B.week12.MVC.service.ITransactionService;
import com.B.week12.MVC.service.ISpringSessionValidator;



@Controller
public class AccountController {
	private static final Logger LOGGER = Logger.getLogger(AccountController.class);

	@Autowired
	IAccountService iAccountService;
	
	 @Autowired
	 ITransactionService iTransactionService;

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
		mav.addObject("fromAccount", fromAccount);
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		List<Transaction> recentTransaction = iAccountService.getTransactionDetails(transaction);

		LOGGER.info("account:" + account);
		LOGGER.info("transaction:" + recentTransaction);
		// mav.addObject("account", account);
		mav.addObject("transaction", recentTransaction);

		return mav;
	}
	
	 @RequestMapping(value = "/forex", method = RequestMethod.GET)
	 public ModelAndView showForex(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	   ModelAndView mav = new ModelAndView("forex");  
	   ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if(!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
	   User user = (User) session.getAttribute("userObject");
	   System.out.println("forex method | " + user.getUserId());
	   //String currency_arr[] = {"USDCAD","USDINR","USDAUD"};
	   ArrayList<String> currency_arr = iAccountService.getAllCurrencies();
	   Account account = new Account();
		account.setUser(user);
		account.setAccountType("checking");
		Account accountDetails = iAccountService.getAccountDetails(account);
	   //request.setAttribute("databaseList", currency_arr);
	   mav.addObject("databaseList",currency_arr);
	   mav.addObject("balance",accountDetails.getCurrentBalance());
	   return mav;
	 }
	 
	 @RequestMapping(value = "/forexProcess", method = RequestMethod.POST)
	 public ModelAndView forexForm(HttpSession session,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("account") Account accounts) {   
	   ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
	   if(!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
	   ModelAndView mav = null;
	   User user = (User) session.getAttribute("userObject");
	   Account account = new Account();
		account.setUser(user);
		account.setAccountType("checking");
		account = iAccountService.getAccountDetails(account);
	   System.out.println(request.getParameter("from_currency"));
	   System.out.println(request.getParameter("to_currency"));
	   double test_from_currency = Double.parseDouble(request.getParameter("from_currency"));
	   String to_currency = "USD"+request.getParameter("to_currency");
	   System.out.println("to currency is | "+to_currency);
	   double forex_amount=0;
	   
	   if(test_from_currency > account.getCurrentBalance()) {
		   System.out.println("Insufficient account balance");
		   mav = new ModelAndView("forex_error");
		   mav.addObject("message","Error: Insufficient account balance current: "+account.getCurrentBalance()+" USD, required: "+test_from_currency+" USD");
	   }
	   else {
		   //double amount = account.getCurrentBalance() - test_from_currency;
		   //double account_balance = account.getCurrentBalance();
		   double converted_amount = iAccountService.processForexTransaction(account,test_from_currency,to_currency);
		   iTransactionService.forexTransaction(account,test_from_currency);
		   System.out.println(test_from_currency);
		   mav = new ModelAndView("forex_success");
		   mav.addObject("forex_amount", forex_amount);
		   //String converted_amount =  String.valueOf(iAccountService.getConvertedAmount(account,test_from_currency,to_currency));
		   mav.addObject("converted_amount", converted_amount+" "+request.getParameter("to_currency"));
	   }   
	   return mav;
	 }
}
