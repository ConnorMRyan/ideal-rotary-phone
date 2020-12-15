package com.AgileNavigate.controller;

import javax.servlet.http.HttpServletRequest;


public interface UserController {

	String signup(HttpServletRequest req);
	String getUser(HttpServletRequest req);
	//public String getAllUsers();
	String getAllUsers(HttpServletRequest req);
	
	String updateUser(HttpServletRequest req);
	
	String signin(HttpServletRequest req);
	String signout(HttpServletRequest req);
	String forgotPassword(HttpServletRequest req);
	
	
}
