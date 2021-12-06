package com.B.week12.MVC.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

  @Autowired
  IUserService iUserService;
  


  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    System.out.println("comes here");
    mav.addObject("login", new Login());

    return mav;
  }
  
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView Logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    session.invalidate();
    System.out.println("comes here");
    mav.addObject("login", new Login());

    return mav;
  }
  



  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("login") Login login) {
    ModelAndView mav = null;

    User user = iUserService.validateUser(login);

    if (null != user) {
      mav = new ModelAndView("redirect:/viewallreservation");
      mav.addObject("firstname", user.getFirstname());
      HttpSession session = request.getSession();
      session.setAttribute("username", user.getUsername());
      session.setAttribute("firstname", user.getFirstname());
      session.setAttribute("lastname", user.getLastname());
    } else {
      mav = new ModelAndView("redirect:/login");
      mav.addObject("message", "Username or Password is wrong!!");
      
    }

    return mav;
  }

}
