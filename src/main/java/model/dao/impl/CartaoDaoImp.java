package model.dao.impl;

import java.util.List;
import model.dao.BaseDAOImp;
import model.dao.daoi.CartaoDAO;
import model.vo.CartaoVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CartaoDaoImp
	extends BaseDAOImp<CartaoVO, Long>
	implements CartaoDAO {

	@Override
	public List<CartaoVO> find(Session s, String value) {
		Query<CartaoVO> qry = s.createQuery(
			"FROM CartaoVO WHERE numero = :numero",
			CartaoVO.class
		);
		qry.setParameter("numero", value);
		return qry.list();
	}

	@Override
	public List<CartaoVO> find(Session s) {
		return s.createQuery("FROM CartaoVO", CartaoVO.class).list();
	}

	@Override
	public CartaoVO findOne(Session s, Long id) {
		return s.get(CartaoVO.class, id);
	}
}
