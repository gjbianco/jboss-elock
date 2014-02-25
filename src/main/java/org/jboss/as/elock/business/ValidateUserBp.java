package org.jboss.as.elock.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.model.Door;

@Stateless
@LocalBean
public class ValidateUserBp {

	public boolean checkCardPermission(Card card, Door door) {
		boolean unlock;

		if (card == null || door == null) {
			unlock = false;
		} else if (card.getPermissionLevel() == door.getRequiredPermission()) {
			unlock = true;
		} else {
			unlock = false;
		}
		return unlock;
	}
}
