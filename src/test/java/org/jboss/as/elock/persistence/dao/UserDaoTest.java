package org.jboss.as.elock.persistence.dao;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import junit.framework.TestCase;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.elock.model.Card;
import org.jboss.as.elock.model.Door;
import org.jboss.as.elock.model.User;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserDaoTest extends TestCase {
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(UserDao.class.getPackage())
				.addClasses(User.class, Card.class, Door.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserDao userDao;
	
	@Inject
	UserTransaction utx;

	@Before
	public void prepareTest() throws Exception {
		userDao.setEntityManager(em);
		clearData();
		insertData();
		startTransaction();
	}
	
	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from User").executeUpdate();
		utx.commit();
	}
	
	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		// insert data
		utx.commit();
		em.clear();
	}
	
	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	private User setUpUserObject() {
		User testUser = new User();
		testUser.setName("Jim Bob");
		testUser.setBirthdate(new Date());
		
		return testUser;
	}
	
	// actual tests -----------------------------------------------------------

	@Test
	public void testCreate() throws Exception {
		User user = setUpUserObject();
//		UserDao userDao = new UserDao();
//		userDao.setEntityManager(em);
		
		userDao.create(user);

		User expected = (User) em.createNamedQuery("findUserById").setParameter("id", user.getId()).getSingleResult();
		
		assertEquals(user.getId(), expected.getId());
	}

/*	@Test
	public void testFindById() {
		
		// get the expected result
		// Query query = em.createNamedQuery("findUserById", User.class);
		User user = setUpUserObject();
		User expected = (User) em.createNamedQuery("findUserById").setParameter("id", user.getId()).getSingleResult();
		// User expected = (User) query.getResultList().get(0);
		
		
		// get the actual result
		UserDao userDao = new UserDao();
		userDao.setEntityManager(em);
		em.getTransaction().begin();
		User actual = userDao.findById(user.getId(), User.class);
		
		assertEquals(expected.getId(), actual.getId());
	}*/

/*	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}*/

/*	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}*/

/*	@Test
	@SuppressWarnings("unchecked")
	public void testFindAll() {
		Query cardQuery = em.createNamedQuery("findAll");
		List<Card> cardList = cardQuery.getResultList();
		List<Card> list = em.createQuery("FROM User").getResultList();
		assertEquals(cardList.size(), list.size());
	}*/
}
