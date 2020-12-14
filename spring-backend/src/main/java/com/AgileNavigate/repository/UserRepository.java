package com.AgileNavigate.repository;

import java.util.List;

import com.AgileNavigate.model.User;
import com.AgileNavigate.model.UserRole;



public interface UserRepository {
	
	void save (User user);
	void save(UserRole role);
	List<User> findAll();
	List<User> findAllByUsername(String username);
	User findByUsername(String username);
	User findUserByUsernameAndPassword(String username, String password);
	User findByFirstNameAndLastName(String firstName, String lastName);
	User findUserByEmail(String email);
	User findUserByUserId(int userId);
	
	boolean updateUser(User user);
	
	boolean isAvailableEmail(String email);
	boolean isAvailableUsername(String username);
}
