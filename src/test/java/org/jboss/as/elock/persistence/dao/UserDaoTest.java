package org.jboss.as.elock.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.TestCase;

import org.jboss.as.elock.model.Card;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest extends TestCase {
	
	@PersistenceContext(unitName = "elockTest")
	EntityManager testEntityManager;
	
	EntityManagerFactory factory;

	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory("elockTest");
		testEntityManager = factory.createEntityManager();
	}

/*	@Test
	public void testCreate() {
		//Query query = testEntityManager.createNamedQuery(name)
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testFindAll() {
		Query cardQuery = testEntityManager.createNamedQuery("findAll");
		List<Card> cardList = cardQuery.getResultList();
		List<Card> list = testEntityManager.createQuery("FROM User").getResultList();
		assertEquals(cardList.size(), list.size());
	}

}
