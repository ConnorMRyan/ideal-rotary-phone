package com.reinertisa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reinertisa.model.Task;
import com.reinertisa.model.TaskCategory;
import com.reinertisa.model.TaskPriority;
import com.reinertisa.model.TaskStatus;
import com.reinertisa.model.User;


@Repository("taskRepository")
@Transactional
public class TaskRepositoryHibernate implements TaskRepository{
	
	private static final Logger logger = Logger.getLogger(TaskRepositoryHibernate.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public TaskRepositoryHibernate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int save(Task task) {
		return (int) sessionFactory.getCurrentSession().save(task);		
	}

	@Override
	public void save(TaskStatus status) {
		sessionFactory.getCurrentSession().save(status);
	}

	@Override
	public void save(TaskPriority priority) {
		sessionFactory.getCurrentSession().save(priority);
	}

	@Override
	public void save(TaskCategory category) {
		sessionFactory.getCurrentSession().save(category);
	}

	@SuppressWarnings("unchecked")
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Task.class).list();
	}

	@Override
	public boolean update(Task task) {
		boolean ret = false;
		try {
			sessionFactory.getCurrentSession().update(task);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Task> findByEmployee(int id) {

		List<Task> tasks = null;
		try {
			String sql = "from Task where employee_id = : id";
			TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery(sql, Task.class);
			query.setParameter("id", id);
			tasks = query.getResultList();
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return tasks;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Task> findByManager(int id) {
		List<Task> tasks = null;
		try {
			String sql = "from Task where manager_id = : id";
			TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery(sql, Task.class);
			query.setParameter("id", id);
			tasks = query.getResultList();
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return tasks;
	}

	@Override
	public Task findTaskByTaskId(int taskId) {
		try {			
			
			String sql = "from Task WHERE taskId= :taskId";
			
			TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery(sql, Task.class);
			query.setParameter("taskId", taskId);
			
			Task task = query.getSingleResult();
			
			return task;
			
		} catch (Exception e) {
			logger.debug(e);
		}	
		
		return null;
	}


}