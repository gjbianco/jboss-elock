package org.jboss.as.elock.persistence.dao;

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
import org.jboss.as.elock.persistence.dao.door.DoorDao;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DoorDaoTest extends TestCase {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(DoorDao.class.getPackage())
				.addClasses(User.class, Card.class, Door.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	EntityManager em;

	@Inject
	DoorDao doorDao;

	@Inject
	UserTransaction utx;

	@Before
	public void prepareTests() throws Exception {
		doorDao.setEntityManager(em);
		clearData();
		startTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	// setup helper functions -------------------------------------------------

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Door");
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	private Door setUpDoorObject() {
		Door door = new Door();
		door.setRequiredPermission(2);
		return door;
	}

	// test helper functions --------------------------------------------------

	private void insertDoor(Door door) {
		em.persist(door);
		em.flush();
		em.refresh(door);
	}

	// actual tests -----------------------------------------------------------

	@Test
	public void testCreate() {
		Door door = setUpDoorObject();

		doorDao.create(door);
		Door expected = doorDao.findById(door.getId(), Door.class);
		assertNotNull(expected);
		assertEquals(door, expected);
	}

	@Test
	public void testFindById() {
		Door door = setUpDoorObject();
		insertDoor(door);
		Door actual = doorDao.findById(door.getId(), Door.class);
		assertEquals(door, actual);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDelete() {
		Door door = setUpDoorObject();
		insertDoor(door);
		doorDao.delete(door.getId(), Door.class);
		List<Door> foundDoors = em.createQuery("FROM Door").getResultList();
		for(Door found : foundDoors) {
			assert(door.getId() != found.getId());
		}
	}

	@Test
	public void testUpdate() {
		int original = 3;
		int updated = 2;

		Door door = setUpDoorObject();

		door.setRequiredPermission(original);
		insertDoor(door);
		Door actual = (Door) em.createQuery("FROM Door d WHERE d.id = :id")
				.setParameter("id", door.getId()).getSingleResult();
		assertEquals(actual, door);

		door.setRequiredPermission(updated);
		doorDao.update(door);
		actual = (Door) em.createQuery("FROM Door d WHERE d.id = :id")
				.setParameter("id", door.getId()).getSingleResult();
		assertEquals(actual, door);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFindAll() {
		int amount = 10;
		for (int i = 0; i < amount; i++) {
			insertDoor(setUpDoorObject());
		}

		List<Door> actual = doorDao.findAll(Door.class);
		List<Door> expected = em.createQuery("FROM Door").getResultList();
		assertEquals(expected.size(), amount);
		assertEquals(expected.size(), actual.size());
	}
}
