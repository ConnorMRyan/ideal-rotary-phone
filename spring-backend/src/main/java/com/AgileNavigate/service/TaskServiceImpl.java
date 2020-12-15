package com.AgileNavigate.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.AgileNavigate.model.*;
import com.AgileNavigate.repository.TaskRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service("taskService")
public class TaskServiceImpl implements TaskService{

	private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	UserService userService;
	
	public TaskServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addTask(Task task) {
		taskRepository.save(task);
		return task.getTaskId() != 0;
	}
	
	@Override
	public boolean createTaskStatusTable(TaskStatus status) {
		taskRepository.save(status);
		return status.getStatusId() != 0;
	}

	@Override
	public boolean createTaskPriorityTable(TaskPriority priority) {
		taskRepository.save(priority);
		return priority.getPriorityId() != 0;
	}

	@Override
	public boolean createTaskCategoryTable(TaskCategory category) {
		taskRepository.save(category);
		return category.getCategoryId() != 0;
	}

	@Override
	public String addTask(HttpServletRequest req) {
		
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader(req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			Task newTask = getTaskInstance(rootobj);		
			
			System.out.println(newTask);
			
			int taskId = taskRepository.save(newTask);

			System.out.println("Task Id" + taskId);
			
			if (taskId > 0) {
				newTask.setTaskId(taskId);
				params = getParams(newTask);
				
			} else {
				System.out.println(taskId);
				params.addProperty("status", "Failed to save task");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "Failed to save task");
			return null;
			
		}

		
		System.out.println(gson.toJson(params));
		return gson.toJson(params);
	}

	@Override
	public String updateTask(HttpServletRequest req) {
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json;
		
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader(req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			Task updateTask = getTaskInstance(rootobj);	
			
		//	updateTask.setEmployee(employee);
					
			int employeeId = rootobj.get("employeeId").getAsInt();
			int taskId = rootobj.get("taskId").getAsInt();
			int taskStatusId = rootobj.get("taskStatus").getAsInt();
			
			LocalDateTime taskCompletedDate = LocalDateTime.now();
			
			if(taskStatusId == 3) {
				
			} else {
				
			}
			
			
			
			
			
			
//			
//			if (taskRepository.update(task)) {
//				params = getParams(task);
//			} else {
//				params.addProperty("status", "Failed to update task");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "Failed to update task");
			
		}
		
		
		return gson.toJson(params);
	}



	@Override
	public String getAllTasks() {

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = null;
		try {
			List<Task> tasks = taskRepository.findAll();
			
			if (tasks == null || tasks.size() == 0) {
				params.addProperty("status", "no record");
				json = gson.toJson(params);
			} else {
				JsonArray jobj = new JsonArray();
				
				for (Task t: tasks) {					
					jobj.add(getParams(t));
				}
				
				json = jobj.toString();
			}
			
		} catch (Exception e) {
			logger.debug(e);
			params = new JsonObject();
			params.addProperty("status", "Failed to get All tasks");
			json = gson.toJson(params);
		}
		return json;
	}

	

	@Override
	public String getTask(HttpServletRequest req) {
		
		
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = "";
		
		try {

			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader(req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();

			int taskId = rootobj.get("taskId").getAsInt();
			System.out.println("TaskId: " + taskId);
			Task task = taskRepository.findTaskByTaskId(taskId);
			System.out.println(task);
			if (task == null) {
				params.addProperty("status", "no task found");
				json = gson.toJson(params);
			} else {
		
			
				params.addProperty("taskId", task.getTaskId());
				params.addProperty("taskName", task.getTaskName());
				params.addProperty("manager", task.getManager().getFirstName() + " " + task.getManager().getLastName());
				params.addProperty("taskPriority", task.getTaskPriority().getPriority());
				params.addProperty("taskCategory", task.getTaskCategory().getCategory());
				params.addProperty("taskStatus", task.getTaskStatus().getStatus());
				params.addProperty("description", task.getDescription());
				params.addProperty("employee", task.getEmployee().getFirstName() + " " + task.getEmployee().getLastName());
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String taskDueDateFormatted = task.getTaskDueDate().format(formatter);
				params.addProperty("taskDueDate", taskDueDateFormatted);
				
				String taskSubmittedDateFormatted = task.getTaskSubmittedDate().format(formatter);
				params.addProperty("taskSubmittedDate", taskSubmittedDateFormatted);
				
				json = gson.toJson(params);
			}

		} catch (Exception e) {
			params.addProperty("status", "no task found");
			json = gson.toJson(params);
		}

		return json;
		
		
	}
	
	/**
	 * Get Tasks by Employee they belong to
	 */
	@Override
	public String getTaskByEmployee(HttpServletRequest req) {
		
		List<Task> tasks = null;
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = null;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader(req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			int id = rootobj.get("userId").getAsInt();
			//String username = rootobj.get("username").getAsString();
			//User u = userService.getUserByUsername(username);
			
			tasks = taskRepository.findByEmployee(id);
			
			JsonArray jobj = new JsonArray();
			if (tasks == null || tasks.size() == 0) {
				params.addProperty("status", "no record");
				json = gson.toJson(params);
			} else {
				
				
				for (Task t: tasks) {					
					jobj.add(getParams(t));
				}
				json = jobj.toString();
			}
			
		} catch (Exception e) {
			logger.debug(e);
			params = new JsonObject();
			params.addProperty("status", "Failed to get Employee tasks");
			json = gson.toJson(params);
		}
		
		return json;
	}

	/**
	 * Get tasks by Manager they belong to
	 */
	@Override
	public String getTaskByManager(HttpServletRequest req) {
		List<Task> tasks = null;
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();
		String json = null;
		
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader(req.getInputStream()));
			JsonObject rootobj = root.getAsJsonObject();
			
			int id = rootobj.get("userId").getAsInt();
			
			tasks = taskRepository.findByManager(id);
			
			JsonArray jobj = new JsonArray();
			if (tasks == null || tasks.size() == 0) {
				params.addProperty("status", "no record");
				json = gson.toJson(params);
			} else {
				
				for (Task t: tasks) {					
					jobj.add(getParams(t));
				}
				json = jobj.toString();
			}
			
			
		} catch (Exception e) {
			logger.debug(e);
			params = new JsonObject();
			params.addProperty("status", "Failed to get Manager tasks");
			json = gson.toJson(params);
		}
		
		return json;
	}
	
	/**
	 * Create a Task object based off of Json task representation fields 
	 * 
	 * @param rootobj - JsonObject consisting of params to make Task object of
	 * @return - Task object representing params in JsonObject
	 * @throws Exception - to be handled in caller's try catch
	 */
	private Task getTaskInstance(JsonObject rootobj) throws Exception {
		
		
		String taskName = rootobj.get("taskName").getAsString();
		int categoryId = rootobj.get("taskCategory").getAsInt();
		
		
		String description = rootobj.get("description").getAsString();

		int priorityId = rootobj.get("taskPriority").getAsInt();
		
	//	String username = rootobj.get("employee").getAsString();
		
	//	User employee = userService.getUserByUsername(username);
	
		String employee = rootobj.get("employee").getAsString();
		System.out.println(employee);
		String[] employeeInfo = employee.split(" ");
		
		User user = userService.getUser(employeeInfo[0], employeeInfo[1]);
		
		int managerId = rootobj.get("managerId").getAsInt();
		
		LocalDateTime taskSubmittedDate = LocalDateTime.now();
		
		String taskDueDate = rootobj.get("taskDueDate").getAsString();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime formattedTaskDueDate = LocalDateTime.parse(taskDueDate, formatter);
			
		return new Task(taskName, new TaskCategory(categoryId), new TaskStatus(1, "Pending"),
		description, new TaskPriority(priorityId), user, new User(managerId),
		taskSubmittedDate, formattedTaskDueDate);

		
		
//		return new Task(taskName, new TaskCategory(categoryId), new TaskStatus(1, "Pending"),
//				description, new TaskPriority(priorityId), employee, new User(managerId), 
//				taskSubmittedDate, formattedTaskDueDate);
		
	}
	


	/**
	 * Build JsonObject params from Task object
	 * 
	 * @param t - task object to be parameterized
	 * @return - JsonObject of fields for JSON representation of task
	 */
	private JsonObject getParams(Task t) {
		JsonObject params = new JsonObject();
		
		params.addProperty("taskId", t.getTaskId());
		params.addProperty("taskName", t.getTaskName());
		params.addProperty("taskCategory", t.getTaskCategory().getCategory());
		params.addProperty("taskStatus", t.getTaskStatus().getStatus());
		params.addProperty("description", t.getDescription());
		params.addProperty("taskPriority", t.getTaskPriority().getPriority());
		if (t.getEmployee() != null) {
			params.addProperty("employee", t.getEmployee().getFirstName() + " " + t.getEmployee().getLastName());
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
	
	/**
	 * Get Task Category object for JPA Persistence 
	 * @param category - category name
	 * @return - instance of TaskCategory where category = category name, OR null
	 */
	private TaskCategory getTaskCategory(String category) {
		TaskCategory ret = null;
		
		if (category.equals("Planning")) {
			ret = new TaskCategory(1, category);
		} else if (category.equals("Analysis")) {
			ret = new TaskCategory(2, category);
		} else if (category.equals("Design")) {
			ret = new TaskCategory(3, category);
		} else if (category.equals("Development")) {
			ret = new TaskCategory(4, category);
		} else if (category.equals("Testing")) {
			ret = new TaskCategory(5, category);
		} else if (category.equals("Implementation")) {
			ret = new TaskCategory(6, category);
		}
		
		return ret;
	}
	
	/**
	 * Get TaskStatus object for JPA Persistence 
	 * @param status - status name
	 * @return - instance of TaskStatus where status = status name, OR null
	 */
	private TaskStatus getTaskStatus(String status) {
		TaskStatus ret = null;
		if (status.equals("Pending")) {
			ret = new TaskStatus(1, status);
		} else if (status.equals("In Progress")) {
			ret = new TaskStatus(2, status);
		} else if (status.equals("In Test")) {
			ret = new TaskStatus(3, status);
		}
		return ret;
	}
	
	/**
	 * Get Task Priority object for JPA Persistence 
	 * @param priority - priority name
	 * @return - instance of TaskPriority where priority = priority name, OR null
	 */
	private TaskPriority getTaskPriority(String priority) {
		TaskPriority ret = null;
		
		if (priority.equals("Do Now")) {
			ret = new TaskPriority(1, priority);
		} else if (priority.equals("Do Next")) {
			ret = new TaskPriority(2, priority);
		} else if (priority.equals("Do Last")) {
			ret = new TaskPriority(3, priority);
		} else if (priority.equals("Do Never")) {
			ret = new TaskPriority(4, priority);
		}
		return ret;
	}

	

}
