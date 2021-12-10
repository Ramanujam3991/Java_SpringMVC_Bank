package com.B.week12.MVC.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.B.week12.MVC.model.Account;
import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.IAccountService;
import com.B.week12.MVC.service.IDashboardService;
import com.B.week12.MVC.service.ISpringSessionValidator;
import com.B.week12.MVC.service.ITransactionService;
import com.B.week12.MVC.service.IUserService;
import com.B.week12.MVC.service.UserService;

@Controller
public class DashboardController {

	@Autowired
	public IDashboardService iDashboardService;

	@Autowired
	ITransactionService iTransactionService;

	@RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard( HttpSession session,
			HttpServletRequest request, HttpServletResponse response, Model m) {

		ISpringSessionValidator validateSession = springSession -> (User) springSession
				.getAttribute("userObject") != null ? true : false;
		if (!validateSession.IS_SESSION_VALID(session))
			return new ModelAndView("redirect:/login");


		ModelAndView mav = new ModelAndView("Dashboard");
		System.out.println("Enters Dashboard View");

		User user = (User) session.getAttribute("userObject");
		List<Account> accountLst = iTransactionService.getAccounts(user.getUserId());
		System.out.println("Controller User " + user.getUsername());
		//m.addAttribute("accountlst", accountLst);
		mav.addObject("user", user);
		mav.addObject("accountlst", accountLst);
		return mav;
	}
}
