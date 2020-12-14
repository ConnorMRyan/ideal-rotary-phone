package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;


public interface UserController {

	public String signup(HttpServletRequest req);
	public String getUser(HttpServletRequest req);
	//public String getAllUsers();
	public String getAllUsers(HttpServletRequest req);
	
	String updateUser(HttpServletRequest req);
	
	String signin(HttpServletRequest req);
	String signout(HttpServletRequest req);
	String forgotPassword(HttpServletRequest req);
	
	
}
