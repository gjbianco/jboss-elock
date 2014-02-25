package org.jboss.as.elock.business;

import java.util.List;

public interface ElockBp<T> {

	public T create(T t);

	public T findById(Long id, Class<T> type);

	public T update(T t);

	public List<T> findAll(Class<T> type, int startIndex, int quantity);

}
