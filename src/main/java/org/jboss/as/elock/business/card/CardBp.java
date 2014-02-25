package org.jboss.as.elock.business.card;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.as.elock.business.AbstractBp;
import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.persistence.dao.card.CardDao;

@Stateless
@LocalBean
public class CardBp extends AbstractBp<Card> {

	@Inject
	public void initDao(CardDao dao) {
		this.dao = dao;
	}

}
