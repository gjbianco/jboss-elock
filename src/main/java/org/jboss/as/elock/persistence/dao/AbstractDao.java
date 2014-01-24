package org.jboss.as.elock.persistence.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDao<T> implements Dao<T> {
	
	@PersistenceContext(unitName = "primary")
	EntityManager entityManager;

	@Override
	public T create(T t) {
		this.entityManager.persist(t);
		this.entityManager.flush();
		this.entityManager.refresh(t);
		return t;
	}

	@Override
	public T findById(Long id, Class<T> type) {
		return this.entityManager.find(type, id);
	}

	@Override
	public void delete(Long id, Class<T> type) {
		Object ref = this.entityManager.getReference(type, id);
		this.entityManager.remove(ref);
	}
	
	@Override
	public T update(T t) {
		entityManager.merge(t);
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> type, int start, int quantity) {
		Query query = entityManager.createQuery("FROM" + type.getName());
		setItemsReturned(query, start, quantity);
		List<T> toReturn = query.getResultList();
		Collections.sort(toReturn, new EntityComparator());
		return toReturn;
	}
}
