package com.AgileNavigate.service;

import javax.servlet.http.HttpServletRequest;

import com.AgileNavigate.model.Task;
import com.AgileNavigate.model.TaskCategory;
import com.AgileNavigate.model.TaskPriority;
import com.AgileNavigate.model.TaskStatus;

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
