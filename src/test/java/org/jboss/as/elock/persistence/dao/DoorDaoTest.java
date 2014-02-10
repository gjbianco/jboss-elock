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
	
	// actual tests -----------------------------------------------------------

	@Test
	public void testCreate() {
		Door door = setUpDoorObject();

		doorDao.create(door);
        Door expected = (Door) em.createQuery("FROM Door d WHERE d.id = :id").setParameter("id", door.getId()).getSingleResult();
        assertNotNull(expected);
		assertEquals(door.getId(), expected.getId());
	}

	@Test
	public void testFindById() {
		Door door = setUpDoorObject();
		insertDoor(door);
		Door actual = doorDao.findById(door.getId(), Door.class);
		assertEquals(door.getId(), actual.getId());
	}

	@Test
	public void testDelete() {
		Door door = setUpDoorObject();
		insertDoor(door);
		doorDao.delete(door.getId(), Door.class);
		assertEquals(doorDao.findById(door.getId(), Door.class), null);
	}

	@Test
	public void testUpdate() {
		Door door = setUpDoorObject();
		door.setRequiredPermission(3);
		insertDoor(door);
		door.setRequiredPermission(2);
		doorDao.update(door);
		assertEquals(doorDao.findById(door.getId(), Door.class).getRequiredPermission(), door.getRequiredPermission());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFindAll() {
		List<Door> actual = doorDao.findAll(Door.class);
		List<Door> expected = em.createQuery("FROM Door").getResultList();
		assertEquals(expected.size(), actual.size());
	}
	
	private void insertDoor(Door door) {
		em.persist(door);
		em.flush();
		em.refresh(door);
	}
}
