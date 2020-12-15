package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;


public interface UserController {

<<<<<<< Updated upstream
	public String signup(HttpServletRequest req);
	public String getUser(HttpServletRequest req);
	//public String getAllUsers();
	public String getAllUsers(HttpServletRequest req);
=======
<<<<<<< Updated upstream:spring-backend/src/main/java/com/AgileNavigate/controller/UserController.java
	String signup(HttpServletRequest req);
	String getUser(HttpServletRequest req);
	//public String getAllUsers();
	String getAllUsers(HttpServletRequest req);
=======
	public String signup(HttpServletRequest req);
	public String getUser(HttpServletRequest req);
	public String getAllUsers();
	//public String getAllUsers(HttpServletRequest req);
>>>>>>> Stashed changes:project-2/src/main/java/com/reinertisa/controller/UserController.java
>>>>>>> Stashed changes
	
	String updateUser(HttpServletRequest req);
	
	String signin(HttpServletRequest req);
	String signout(HttpServletRequest req);
	String forgotPassword(HttpServletRequest req);
	
	
}
