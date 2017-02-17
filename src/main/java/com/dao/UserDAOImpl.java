package com.dao;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.Query;

import com.model.User;
import com.util.LogUtil;

public class UserDAOImpl extends GenericDAO implements UserDAO {

	@Override
	public List<User> getAllUsers() {
		return findAll(User.class);
	}

	@Override
	public boolean createUser(User user) {
		return save(user);
	}

	@Override
	public boolean updateUser(User user) {
		return update(user);
	}

	@Override
	public User findById(int userId) {
		return findById(User.class, userId);
	}

	public boolean deleteUser(User user) {
		return delete(User.class, user);
	}

	@Override
	public User findByEmail(String email) {
		LogUtil.log("finding user with email: " + email, Level.INFO, null);
		try {
			if (session.getTransaction() != null
					&& session.getTransaction().isActive()) {
				session.getTransaction();
			} else {
				session.beginTransaction();
			}

			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT user ").append("FROM User user ")
					.append("WHERE user.email = :email ");
			Query q = session.createQuery(queryString.toString());
			q.setParameter("email", email).uniqueResult();

			return (User) q.uniqueResult();

		} catch (RuntimeException re) {
			LogUtil.log("find user by email failed", Level.SEVERE, re);
			throw re;
		}
	}
}
