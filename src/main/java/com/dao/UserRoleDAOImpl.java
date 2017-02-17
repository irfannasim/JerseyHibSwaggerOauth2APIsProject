package com.dao;

import java.util.List;

import com.model.UserRole;

public class UserRoleDAOImpl extends GenericDAO implements UserRoleDAO {

	@Override
	public List<UserRole> loadAllUserRoles() {
		return findAll(UserRole.class);
	}

	@Override
	public UserRole findById(int userRoleId) {
		return findById(userRoleId);
	}

	@Override
	public List<UserRole> findByListOfIds(List<Integer> userRoleIds) {
		return findByListOfIds(UserRole.class, "Id", userRoleIds);
	}
}
