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
		User user =(User)session.getAttribute("userObject");
		
		
		LOGGER.info("transaction:::::"+transaction);
		iTransactionService.depositMoney(transaction);

		return new ModelAndView("redirect:/depositMoney");
	}

}
