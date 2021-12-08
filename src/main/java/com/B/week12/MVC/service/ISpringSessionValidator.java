package com.B.week12.MVC.service;

import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface ISpringSessionValidator {
	public boolean IS_SESSION_VALID(HttpSession session);
}

