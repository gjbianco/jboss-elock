package org.jboss.as.elock.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;

public class UserDaoTest extends TestCase {
	

	@PersistenceContext(unitName = "elockTest")
	EntityManager testEntityManager;
	
	EntityManagerFactory factory;

	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory("elockTest");
		testEntityManager = factory.createEntityManager();
	}

	public void test(){
		Assert.assertEquals(true, true);
	}
	

	//@Before
	public void setup() {
		System.out.println("\n\ngetting ready to setup");
		// System.out.println
		factory = Persistence.createEntityManagerFactory("elockTest");
		// System.out.println("Yeah, factory");
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

//	@Test
//	public void testFindAll() {
//		Query cardQuery = testEntityManager.createNamedQuery("findAll");
//		List<Card> cardList = cardQuery.getResultList();
//		List<Card> list = testEntityManager.createQuery("FROM User").getResultList();
//		assertEquals(cardList.size(), list.size());
//	}

}
