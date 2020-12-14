package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.model.User;
import com.reinertisa.repository.TaskRepository;
import com.reinertisa.service.TaskServiceImpl;

@SuppressWarnings("deprecation")
@RunWith(JUnit4ClassRunner.class)
public class TaskServiceTests {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Mock
	private TaskRepository taskRepository;
	
	@Autowired
	@InjectMocks
	private TaskServiceImpl taskService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	private Task getTask(int id) {
		return new Task(id, "project-2", new TaskCategory(1, "Planning"), 
				new TaskStatus(1, "Pending"), "Thank you",
				new TaskPriority(1, "Do Now"), new User(2), LocalDateTime.now(), 
				new User(3), LocalDateTime.now(), LocalDateTime.now());
	}
	
	private JsonObject getParams(Task t) {
		JsonObject params = new JsonObject();
		
		params.addProperty("taskId", t.getTaskId());
		params.addProperty("taskName", t.getTaskName());
		params.addProperty("taskCategory", t.getTaskCategory().getCategory());
		params.addProperty("taskStatus", t.getTaskStatus().getStatus());
		params.addProperty("description", t.getDescription());
		params.addProperty("taskPriority", t.getTaskPriority().getPriority());
		if (t.getEmployee() != null) {
			params.addProperty("employee", t.getEmployee().getUsername());
		} else {
			params.addProperty("employee", "null");
		}
		
		if (t.getTaskCompletedDate() == null) {
			params.addProperty("taskCompletedDate", "null");
		} else {
			params.addProperty("taskCompletedDate", t.getTaskCompletedDate().format(formatter));
		}
		
		params.addProperty("manager", t.getManager().getUsername());
		params.addProperty("taskSubmittedDate", t.getTaskSubmittedDate().format(formatter));
		if (t.getTaskDueDate() != null ) {
			params.addProperty("taskDueDate", t.getTaskDueDate().format(formatter));
		} else {
			params.addProperty("taskDueDate", "null");
		}
		
		return params;
		
		
	}
	
	@Test
	public void getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		
		assertEquals(jobj.toString(), taskService.getAllTasks());
		
	}
	
	@Test
	public void getTasksFail() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		Mockito.when(taskRepository.findAll()).thenReturn(null);
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		System.out.println(taskService.getAllTasks());
		assertEquals("{\"status\":\"no record\"}", taskService.getAllTasks());
		
	}
	
	@Test
	public void getTasksException() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(getTask(1));
		tasks.add(getTask(2));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		Mockito.when(taskRepository.findAll()).thenThrow(new RuntimeException());
		JsonArray jobj = new JsonArray();
		for (Task t: tasks) {					
			jobj.add(getParams(t));
		}
		System.out.println(taskService.getAllTasks());
		assertEquals("{\"status\":\"Failed to get All tasks\"}", taskService.getAllTasks());
		
	}
	
	
}
