package com.dao;

import java.util.List;

import com.model.UserRole;

public interface UserRoleDAO {
	public List<UserRole> loadAllUserRoles();
	public UserRole findById(int userRoleId);
	public List<UserRole> findByListOfIds(List<Integer> userRoleIds);

}
