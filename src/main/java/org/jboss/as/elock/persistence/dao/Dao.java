package org.jboss.as.elock.persistence.dao;

import java.util.List;

public interface Dao<T> {

	/**
	 * Persists the entity t, adding it to the database.
	 * 
	 * @param t - the object to be persisted
	 * @return the managed entity
	 */
	public T create(T t);

	/**
	 * Given a database key id and the entity type to return, finds the entity in the database.
	 * 
	 * @param id - the id to search by
	 * @param type - the class type of the entity to be returned
	 * @return the entity found with the id, or null if it doesn't exist
	 */
	public T findById(Long id, Class<T> type);

	/**
	 * Deletes the entity, determined by the id from the database.
	 * 
	 * @param id - the id of the entity to be deleted
	 * @param type - the class type of the entity to be deleted
	 */
	public void delete(Long id, Class<T> type);

	/**
	 * Given a modified entity that exists on the database, updates the database with the changed entity.
	 * 
	 * @param t - the entity to be updated
	 * @return the updated, managed entity
	 */
	public T update(T t);

	/**
	 * Given a type, the starting index, and the number to return, returns an 
	 * unfiltered List<T> of entities in this database table.
	 * 
	 * @param type - the class type of the entities to be returned
	 * @param startIndex - the position of the first item to be returned
	 * @param quantity - the number of items to return. If this is equal to 0, returns all items.
	 * @return A List<T> containing a number of elements determined by the quantity field.
	 */
	public List<T> findAll(Class<T> type);
}