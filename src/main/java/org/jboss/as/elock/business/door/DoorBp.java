package org.jboss.as.elock.business.door;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.as.elock.business.AbstractBp;
import org.jboss.as.elock.model.Door;
import org.jboss.as.elock.persistence.dao.door.DoorDao;

@Stateless
@LocalBean
public class DoorBp extends AbstractBp<Door> {

	@Inject
	public void initDao(DoorDao dao) {
		this.dao = dao;
	}

}
