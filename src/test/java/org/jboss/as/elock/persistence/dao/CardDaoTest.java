package org.jboss.as.elock.persistence.dao;

import java.util.Date;
import java.util.List;

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
public class CardDaoTest extends TestCase {
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(CardDao.class.getPackage())
				.addClasses(User.class, Card.class, Door.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	CardDao cardDao;
	
	@Inject
	UserTransaction utx;

	@Before
	public void prepareTest() throws Exception {
		cardDao.setEntityManager(em);
		clearData();
		startTransaction();
	}
	
	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Card");
		utx.commit();
	}
	
	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	private Card setUpCardObject() {
		Card card = new Card();
		card.setExpiration(new Date());
		return card;
	}

	@Test
	public void testCreate() {
		Card card = setUpCardObject();

		Card expected;
		try {
			expected = (Card) em.createNamedQuery("findCardById").setParameter("id", card.getId()).getSingleResult();
			fail("Should not find card before we create it.");
		} catch(javax.persistence.NoResultException e) {
			// correct
		}

		cardDao.create(card);
        expected = (Card) em.createNamedQuery("findCardById").setParameter("id", card.getId()).getSingleResult();

		assertEquals(card.getId(), expected.getId());
	}

	@Test
	public void testFindById() {
		Card card = setUpCardObject();
		cardDao.create(card);
		Card actual = cardDao.findById(card.getId(), Card.class);
		assertEquals(card.getId(), actual.getId());
	}

	@Test
	public void testDelete() {
		Card card = setUpCardObject();
		cardDao.create(card);
		cardDao.delete(card.getId(), Card.class);
		assertEquals(cardDao.findById(card.getId(), Card.class), null);
	}

	@Test
	public void testUpdate() {
		Card card = setUpCardObject();
		card.setPermissionLevel(3);
		cardDao.create(card);
		card.setPermissionLevel(2);
		cardDao.update(card);
		assertEquals(cardDao.findById(card.getId(), Card.class).getPermissionLevel(), card.getPermissionLevel());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFindAll() {
		List<Card> actual = cardDao.findAll(Card.class);
		List<Card> expected = em.createNamedQuery("findAllCards").getResultList();
		assertEquals(expected.size(), actual.size());
	}
}
