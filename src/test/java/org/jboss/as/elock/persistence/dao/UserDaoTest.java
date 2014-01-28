package org.jboss.as.elock.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.TestCase;

import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.model.User;
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

	@Test
	public void testCreate() {
		User user = setUpUserObject();
		UserDao userDao = new UserDao();
		userDao.setEntityManager(testEntityManager);
		
		testEntityManager.getTransaction().begin();
		userDao.create(user);

		User expected = (User) testEntityManager.createNamedQuery("findUserById").setParameter("id", user.getId()).getSingleResult();
		testEntityManager.getTransaction().commit();
		
		assertEquals(user.getId(), expected.getId());
	}

	@Test
	public void testFindById() {
		
		// get the expected result
		// Query query = testEntityManager.createNamedQuery("findUserById", User.class);
		User user = setUpUserObject();
		User expected = (User) testEntityManager.createNamedQuery("findUserById").setParameter("id", user.getId()).getSingleResult();
		// User expected = (User) query.getResultList().get(0);
		
		
		// get the actual result
		UserDao userDao = new UserDao();
		userDao.setEntityManager(testEntityManager);
		testEntityManager.getTransaction().begin();
		User actual = userDao.findById(user.getId(), User.class);
		
		assertEquals(expected.getId(), actual.getId());
	}

	/*@Test
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

	private User setUpUserObject() {
		User testUser = new User();
		testUser.setId(1L);
		testUser.setName("Jim Bob");
		testUser.setBirthdate(new Date());
		
		return testUser;
	}
}
