package com.AgileNavigate.service;

import com.AgileNavigate.model.User;
import com.AgileNavigate.model.UserRole;

import javax.servlet.http.HttpServletRequest;



public interface UserService {

	String signup(HttpServletRequest req);
	//public String getAllUsers();
	//public String getAllUsers(HttpServletRequest req);
	User getUserByUsername(String username);
	User getUser(String firstName, String lastName);
	String getUser(HttpServletRequest req);
	String updateUser(HttpServletRequest req);
	
	
	String signin(HttpServletRequest req);
	String signout(HttpServletRequest req);
	String forgotPass(HttpServletRequest req);
	
	
	boolean createRoleTable(UserRole role);
}
