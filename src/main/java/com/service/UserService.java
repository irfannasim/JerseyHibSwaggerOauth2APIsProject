package com.service;

import java.util.List;

import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.model.User;

public class UserService {

	UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAOImpl();
	}

	public User findById(int userId) {
		return userDAO.findById(userId);
	}

	public User findByEmail(String email) {
		return userDAO.findByEmail(email);

	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public boolean createUser(User user) {
		return userDAO.createUser(user);
	}

	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public boolean deleteUser(User user) {
		return userDAO.deleteUser(user);
	}

}
