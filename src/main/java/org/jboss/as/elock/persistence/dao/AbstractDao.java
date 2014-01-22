package org.jboss.as.elock.persistence.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

public abstract class AbstractDao {
	
	@PersistenceUnit(unitName = "primary")
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Resource
	UserTransaction utx;
}
