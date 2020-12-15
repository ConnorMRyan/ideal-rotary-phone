package com.reinertisa.service;

import javax.servlet.http.HttpServletRequest;

import com.reinertisa.model.User;
import com.reinertisa.model.UserRole;


public interface UserService {

	public String signup(HttpServletRequest req);
	//public String getAllUsers();
	//public String getAllUsers(HttpServletRequest req);
	public User getUserByUsername(String username);
	public User getUser(String firstName, String lastName);
	public String getUser(HttpServletRequest req);
	public String updateUser(HttpServletRequest req);
	
	
	String signin(HttpServletRequest req);
	String signout(HttpServletRequest req);
	String forgotPass(HttpServletRequest req);
	
	
	boolean createRoleTable(UserRole role);
}
