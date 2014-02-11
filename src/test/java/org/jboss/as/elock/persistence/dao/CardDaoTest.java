package org.jboss.as.elock.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	// actual tests -----------------------------------------------------------

	@Test
	public void testCreate() {
		Card card = setUpCardObject();


		cardDao.create(card);

        Card expected = findCardById(card.getId());
        assertNotNull(expected);
		assertEquals(card, expected);
	}

	@Test
	public void testFindById() {
		Card card = setUpCardObject();
		insertCard(card);
		Card actual = cardDao.findById(card.getId(), Card.class);
		assertEquals(card, actual);
	}

	@Test
	public void testDelete() {
		Card card = setUpCardObject();
		insertCard(card);
		cardDao.delete(card.getId(), Card.class);
        Card actual = findCardById(card.getId());
		assertEquals(actual, null);
	}

	@Test
	public void testUpdate() {
		Card card = setUpCardObject();
		card.setPermissionLevel(3);
		insertCard(card);
		card.setPermissionLevel(2);
		cardDao.update(card);
        Card actual = findCardById(card.getId());
		assertEquals(actual, card);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFindAll() {
		List<Card> actual = cardDao.findAll(Card.class);
		List<Card> expected = em.createQuery("FROM Card").getResultList();
		assertEquals(expected.size(), actual.size());
	}
	
	private Card findCardById(Long id) {
		Card found;
		try {
			found = (Card) em.createQuery("FROM Card c WHERE c.id = :id")
        		.setParameter("id", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
        return found;
	}
	
	private void insertCard(Card card) {
		em.persist(card);
		em.flush();
		em.refresh(card);
	}
}
