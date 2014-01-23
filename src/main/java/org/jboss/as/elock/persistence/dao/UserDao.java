package org.jboss.as.elock.persistence.dao;

import org.jboss.as.elock.model.User;

public class UserDao extends AbstractDao {
	
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}
}
