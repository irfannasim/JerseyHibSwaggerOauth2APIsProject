package com.dao;

import java.util.List;
import com.model.User;

public interface UserDAO {

	public List<User> getAllUsers();
	public boolean createUser(User user);
	public boolean updateUser(User user);
	public User findById(int userId);
	public User findByEmail(String email);
	public boolean deleteUser(User user);
}
