package com.service;

import java.util.List;

import com.dao.UserRoleDAO;
import com.dao.UserRoleDAOImpl;
import com.model.UserRole;

public class UserRoleService {

	UserRoleDAO userRoleDAO;

	public UserRoleService() {
		userRoleDAO = new UserRoleDAOImpl();
	}

	public List<UserRole> getAllUserRoles() {
		return userRoleDAO.loadAllUserRoles();
	}

	public UserRole findById(int userRoleId) {
		return userRoleDAO.findById(userRoleId);
	}

	public List<UserRole> findByListOfIds(List<Integer> userRoleIds) {
		return userRoleDAO.findByListOfIds(userRoleIds);
	}
}
