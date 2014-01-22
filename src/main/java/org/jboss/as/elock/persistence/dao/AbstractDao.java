package org.jboss.as.elock.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao {
	
	@PersistenceContext(unitName = "primary")
	protected EntityManager entityManager;
}
