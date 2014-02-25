package org.jboss.as.elock.business;

import java.util.List;

import org.jboss.as.elock.persistence.dao.Dao;

public class AbstractBp<T> implements ElockBp<T> {
	
	protected Dao<T> dao;
	
	 protected void initAbstract(Dao<T> dao) {
	        this.dao = dao;
	    }

	@Override
	public T create(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(Long id, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Class<T> type, int startIndex, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

}
