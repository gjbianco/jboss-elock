package org.jboss.as.elock.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.model.User;

public class UserDao extends AbstractDao<User> {

	@SuppressWarnings("unchecked")
	public List<Card> findCardsByUser(User user) {
		if(user == null)
			return null;

		String hql = "SELECT card FROM User as user " + "JOIN user.cardXUsers AS cardXUser "
				+ "JOIN cardXUser.card AS card " + "WHERE user=:user";
		Query query = entityManager.createQuery(hql);
		query.setParameter("user", user);
		return (List<Card>) query.getResultList();
	}
}
