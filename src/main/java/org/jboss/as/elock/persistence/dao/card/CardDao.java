package org.jboss.as.elock.persistence.dao.card;

import java.util.List;

import javax.persistence.Query;

import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.model.User;
import org.jboss.as.elock.persistence.dao.AbstractDao;

public class CardDao extends AbstractDao<Card> {

	public List<Card> findCardsByUser(User user) {
		if (user == null)
			return null;

		String hql = "SELECT card FROM User as user " + "JOIN user.cardXUsers AS cardXUser "
				+ "JOIN cardXUser.card AS card " + "WHERE user=:user";
		Query query = entityManager.createQuery(hql);
		query.setParameter("user", user);
		return (List<Card>) query.getResultList();
	}

}