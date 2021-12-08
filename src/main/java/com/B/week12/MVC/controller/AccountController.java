package com.B.week12.MVC.controller;

import java.util.HashMap;

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

@Controller
public class AccountController {
private static final Logger LOGGER = Logger.getLogger(AccountController.class);

 @Autowired
 IAccountService iAccountService;
 
 @RequestMapping(value = "/accountDetails/{accountType}", method = RequestMethod.GET)
 public ModelAndView viewAccount(@PathVariable String accountType, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
   ModelAndView mav = new ModelAndView("viewaccount");
   LOGGER.info("Fetching Account Details in account controller and viewing:"+accountType);
   User user = (User) session.getAttribute("userObject");
   HashMap dataMap = new HashMap();
   dataMap.put("accountType", accountType);
   dataMap.put("userId", user.getUserId());
   Account account =  iAccountService.getAccountDetails(dataMap);
   dataMap.put("account", account);
   Transaction transaction =  iAccountService.getTransactionDetails(dataMap);
   transaction.setFromAccount(account);
   LOGGER.info("account:"+account);
   LOGGER.info("transaction:"+transaction);
   //mav.addObject("account", account);
   mav.addObject("transaction", transaction);

   return mav;
 }
}
