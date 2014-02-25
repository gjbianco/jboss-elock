package org.jboss.as.elock.business.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.as.elock.business.AbstractBp;
import org.jboss.as.elock.model.User;
import org.jboss.as.elock.persistence.dao.user.UserDao;

@Stateless
@LocalBean
public class UserBp extends AbstractBp<User> {

	@Inject
	public void initDao(UserDao dao) {
		this.dao = dao;
	}

}
