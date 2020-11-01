package model.dao;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseDAOImp<T, ID>
	implements BaseDAO<T, ID>, Serializable {
	private static final long serialVersionUID = 1L;
	private static Transaction transaction;

	@Override
	public Boolean save(Session s, T o) {
		transaction = s.beginTransaction();
		try {
			s.saveOrUpdate(o);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		return false;
	}

	@Override
	public Boolean delete(Session s, T o) {
		transaction = s.beginTransaction();
		try {
			s.delete(o);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		return false;
	}

	public String like(String value) {
		return "%" + value + "%";
	}
}
