package com.B.week12.MVC.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.B.week12.MVC.model.Login;
import com.B.week12.MVC.model.User;
import com.B.week12.MVC.service.IUserService;

@Controller
public class LoginController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

  @Autowired
  IUserService iUserService;
  


  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new Login());

    return mav;
  }
  
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView Logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    session.invalidate();
    LOGGER.info("Session stopped");
    mav.addObject("login", new Login());

    return mav;
  }
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("login") Login login) {
    ModelAndView mav = null;

    User user = iUserService.validateUser(login);

    if (null != user) {
      mav = new ModelAndView("redirect:/Dashboard");
      session = request.getSession();
      session.setAttribute("userObject", user);
      session.setAttribute("fullname", user.getFirstname()+" "+user.getLastname());
      LOGGER.info("Loggin Successfull, session started for "+user.getFirstname());
    } else {
      mav = new ModelAndView("redirect:/login");
      mav.addObject("message", "Username or Password is wrong!!");
      
    }

    return mav;
  }

}
