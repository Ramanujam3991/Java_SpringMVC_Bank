package com.B.week12.MVC.controller;

import java.util.List;

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
import com.B.week12.MVC.model.MyBills;
import com.B.week12.MVC.model.Transaction;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.ITransactionService;
import com.B.week12.MVC.service.ISpringSessionValidator;

@Controller
public class TransactionController {
	private static final Logger LOGGER = Logger.getLogger(TransactionController.class);

	@Autowired
	ITransactionService iTransactionService;

	@RequestMapping(value = "/depositMoney", method = RequestMethod.GET)
	public ModelAndView depositMoney(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		User user =(User)session.getAttribute("userObject");
		List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
		
		ModelAndView mav = new ModelAndView("depositMoney");
		LOGGER.info("accountLst:::::"+accountLst.get(0));
		mav.addObject("accountLst", accountLst);
		mav.addObject("transaction", new Transaction());

		return mav;
	}
	
	@RequestMapping(value = "/depositAmount", method = RequestMethod.POST)
	public ModelAndView depositMoneySave(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("transaction") Transaction transaction) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		
		
		LOGGER.info("transaction:::::"+transaction);
		iTransactionService.depositMoney(transaction);

		return new ModelAndView("redirect:/Dashboard");
	}
	
	@RequestMapping(value = "/withdrawMoney", method = RequestMethod.GET)
	public ModelAndView withdrawMoney(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		User user =(User)session.getAttribute("userObject");
		List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
		
		ModelAndView mav = new ModelAndView("withdrawMoney");
		LOGGER.info("accountLst:::::"+accountLst.get(0));
		mav.addObject("accountLst", accountLst);
		mav.addObject("transaction", new Transaction());

		return mav;
	}
	
	@RequestMapping(value = "/withdrawMoney", method = RequestMethod.POST)
	public ModelAndView withdrawMoneySave(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("transaction") Transaction transaction) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		
		LOGGER.info("transaction:::::"+transaction);
		iTransactionService.withdrawMoney(transaction);

		return new ModelAndView("redirect:/depositMoney");
	}
	
	@RequestMapping(value = "/transferMoney", method = RequestMethod.GET)
	public ModelAndView transferMoney(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		User user =(User)session.getAttribute("userObject");
		List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
		
		ModelAndView mav = new ModelAndView("transferMoney");
		LOGGER.info("accountLst:::::"+accountLst.get(0));
		mav.addObject("accountLst", accountLst);
		mav.addObject("transaction", new Transaction());

		return mav;
	}
	
	@RequestMapping(value = "/transferMoney", method = RequestMethod.POST)
	public ModelAndView transferMoneySave(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("transaction") Transaction transaction) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		
		LOGGER.info("transaction:::::"+transaction);
		boolean isValidAccount = iTransactionService.checkAccountExists(transaction.getToAccount().getAccountId());
		if(!isValidAccount)
		{
			User user =(User)session.getAttribute("userObject");
			List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
			
			ModelAndView mav = new ModelAndView("transferMoney");
			LOGGER.info("accountLst:::::"+accountLst.get(0));
			mav.addObject("message","Error: Invalid To Account Number");
			mav.addObject("accountLst", accountLst);
			mav.addObject("transaction", transaction);

			return mav;
		}
		iTransactionService.transferMoney(transaction);

		return new ModelAndView("transferMoneyConfirmation");
	}
	@RequestMapping(value = "/registerbiller", method = RequestMethod.GET)
	public ModelAndView registerbiller(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		User user =(User)session.getAttribute("userObject");
		//List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
		
		ModelAndView mav = new ModelAndView("registerbiller");
		//LOGGER.info("accountLst:::::"+accountLst.get(0));
		//mav.addObject("accountLst", accountLst);
		mav.addObject("myBills", new MyBills());

		return mav;
	}
	
	@RequestMapping(value = "/registerbiller", method = RequestMethod.POST)
	public ModelAndView registerbillerSave(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("myBills") MyBills myBills) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		
		 User billerUser = iTransactionService.validateUsername(myBills.getToUser().getUsername());
		 User user =(User)session.getAttribute("userObject");
		 if (null == billerUser) {
			 ModelAndView mav = new ModelAndView("registerbiller");
			 mav.addObject("message","Error: The user information is not valid, please enter a valid username");
			 mav.addObject("myBills",myBills);
			 return mav;
		    } 
		 else
			 iTransactionService.registerBiller(user,billerUser);

		return new ModelAndView("redirect:/payBills");
	}
	@RequestMapping(value = "/payBills", method = RequestMethod.GET)
	public ModelAndView payBills(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		User user =(User)session.getAttribute("userObject");
		List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
		List<Account> payeeLst =  iTransactionService.getPayeeLst(user.getUserId());
		
		ModelAndView mav = new ModelAndView("payBills");
		LOGGER.info("accountLst:::::"+accountLst.get(0));
		mav.addObject("accountLst", accountLst);
		mav.addObject("payeeLst", payeeLst);
		mav.addObject("transaction", new Transaction());

		return mav;
	}
	
	@RequestMapping(value = "/payBills", method = RequestMethod.POST)
	public ModelAndView payBillsSave(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("transaction") Transaction transaction) {
		ISpringSessionValidator validateSession = springSession -> (User) springSession.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session)) return new ModelAndView("redirect:/login");
		
		LOGGER.info("transaction:::::"+transaction);
		boolean isValidAccount = iTransactionService.checkAccountExists(transaction.getToAccount().getAccountId());
		if(!isValidAccount)
		{
			User user =(User)session.getAttribute("userObject");
			List<Account> accountLst =  iTransactionService.getAccounts(user.getUserId());
			List<Account> payeeLst =  iTransactionService.getPayeeLst(user.getUserId());

			ModelAndView mav = new ModelAndView("payBills");
			LOGGER.info("accountLst:::::"+accountLst.get(0));
			mav.addObject("message","Error: Invalid To Account Number");
			mav.addObject("accountLst", accountLst);
			mav.addObject("payeeLst", payeeLst);
			mav.addObject("transaction", transaction);

			return mav;
		}
		iTransactionService.transferMoney(transaction);

		return new ModelAndView("payBillsConfirmation");
	}


}
