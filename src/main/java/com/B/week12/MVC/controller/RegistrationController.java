package com.B.week12.MVC.controller;

import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Constants;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.ISpringSessionValidator;
import com.B.week12.MVC.service.ITransactionService;
import com.B.week12.MVC.service.IUserService;

@Controller
public class RegistrationController {

	@Autowired
	public IUserService iUserService;
	@Autowired
	ITransactionService iTransactionService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response, Model m) {
		System.out.println("comes to register page");
		ModelAndView mav = new ModelAndView("register");

		mav.addObject("account", new Account());
		return mav;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("account") Account account) {

		iUserService.register(account);
		ModelAndView mav = new ModelAndView("registerconfirmation");
		mav.addObject("SUCCESSFUL_REGISTRATION_MSG", Constants.SUCCESSFUL_REGISTRATION_MSG);
		return mav;
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public ModelAndView addAccount(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("addAccount");

		mav.addObject("account", new Account());
		return mav;
	}

	@RequestMapping(value = "/accountRegisterProcess", method = RequestMethod.POST)
	public ModelAndView addAccount(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("account") Account account) {

		ISpringSessionValidator validateSession = springSession -> (User) springSession
				.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session))
			return new ModelAndView("redirect:/login");

		User user = (User) session.getAttribute("userObject");
		List<Account> accountLst = iTransactionService.getAccounts(user.getUserId());
		boolean accountExist = accountLst.stream().filter(acc->acc.getAccountType().equals(account.getAccountType())).findFirst().isPresent(); 
		if(accountExist)
		{
			ModelAndView mav = new ModelAndView("addAccount");

			mav.addObject("account", account);
			mav.addObject("message","Error: Account Type already exists, please choose account type other than \""+account.getAccountType()+"\"");
			return mav;
		}
		account.setUser(user);
		iUserService.createAccount(account);
		ModelAndView mav = new ModelAndView("transferMoneyConfirmation");
		mav.addObject("SUCCESSFUL_REGISTRATION_MSG", Constants.SUCCESSFUL_ACCOUNT_CREATION_MSG);
		return mav;
	}

}
