package com.AgileNavigate.repository;

import java.util.List;

import com.AgileNavigate.model.Task;
import com.AgileNavigate.model.TaskCategory;
import com.AgileNavigate.model.TaskPriority;
import com.AgileNavigate.model.TaskStatus;



public interface TaskRepository {

	int save(Task task);
	void save(TaskStatus status);
	void save(TaskPriority priority);
	void save(TaskCategory category);
	
	List<Task> findAll();
	boolean update(Task task);
	List<Task> findByEmployee(int id);
	List<Task> findByManager(int id);
	
	Task findTaskByTaskId(int taskId);
}
