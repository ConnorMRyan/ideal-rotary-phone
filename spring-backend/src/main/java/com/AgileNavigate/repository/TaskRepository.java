package com.AgileNavigate.repository;

import com.AgileNavigate.model.*;

import java.util.List;



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
