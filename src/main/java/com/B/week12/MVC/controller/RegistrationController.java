package com.B.week12.MVC.controller;
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
import com.B.week12.MVC.service.IUserService;


@Controller
public class RegistrationController {
	
	  @Autowired
	  public IUserService iUserService;
	  
	

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
	  mav.addObject("SUCCESSFUL_REGISTRATION_MSG",Constants.SUCCESSFUL_REGISTRATION_MSG);
	  return mav;
	  }

}
