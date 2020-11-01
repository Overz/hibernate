package model.dao;

import org.hibernate.Session;

public interface BaseDAO<T, ID> {
	Boolean save(Session s, T o);

	Boolean delete(Session s, T o);

	T findOne(Session s, Long id);
}
