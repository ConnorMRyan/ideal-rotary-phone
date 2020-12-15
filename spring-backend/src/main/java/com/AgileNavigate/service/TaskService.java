package com.AgileNavigate.service;


import com.AgileNavigate.model.*;

import javax.servlet.http.HttpServletRequest;

public interface TaskService {


    boolean addTask(Task task);

    boolean createTaskStatusTable(TaskStatus status);

    boolean createTaskPriorityTable(TaskPriority priority);

    boolean createTaskCategoryTable(TaskCategory category);

    String addTask(HttpServletRequest req);

    String updateTask(HttpServletRequest req);

    //String assignTask(HttpServletRequest req);

    String getAllTasks();

    String getTask(HttpServletRequest req);

    String getTaskByEmployee(HttpServletRequest req);

    String getTaskByManager(HttpServletRequest req);
}
