package com.reinertisa.controller;

import javax.servlet.http.HttpServletRequest;

public interface TaskController {
	
	String addTask(HttpServletRequest req);
	String updateTask(HttpServletRequest req);
	//String assignTask(HttpServletRequest req);
	//String getAllTasks();
	String getTask(HttpServletRequest req);
	String getTaskByEmployee(HttpServletRequest req);
	String getTaskByManager(HttpServletRequest req);
	
}
