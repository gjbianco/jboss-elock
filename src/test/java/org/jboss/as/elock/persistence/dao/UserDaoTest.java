package org.jboss.as.elock.persistence.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.as.elock.model.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	
	@PersistenceContext(unitName = "userTest")
	EntityManager testEntityManager;
	
	EntityManagerFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory("elockTest");
		testEntityManager = factory.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
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
	}

	@Test
	public void testFindAll() {
		Query cardQuery = testEntityManager.createNamedQuery("findAll");
		List<Card> cardList = cardQuery.getResultList();
		List<Card> list = testEntityManager.createQuery("FROM User").getResultList();
		assertEquals(cardList.size(), list.size());
	}

}
