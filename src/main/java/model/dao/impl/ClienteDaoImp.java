package model.dao.impl;

import java.util.List;
import model.dao.BaseDAOImp;
import model.dao.daoi.ClienteDAO;
import model.vo.ClienteVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ClienteDaoImp
	extends BaseDAOImp<ClienteVO, Long>
	implements ClienteDAO {

	@Override
	public List<ClienteVO> find(Session s, String value) {
		Query<ClienteVO> qry = s.createQuery(
			"FROM CartaoVO WHERE numero LIKE :numero OR validade LIKE :validade OR bandeira LIKE :bandeira",
			ClienteVO.class
		);
		qry.setParameter("numero", like(value));
		qry.setParameter("validade", like(value));
		qry.setParameter("bandeira", like(value));
		return qry.list();
	}

	@Override
	public List<ClienteVO> find(Session s) {
		return s.createQuery("FROM ClienteVO", ClienteVO.class).list();
	}

	@Override
	public ClienteVO findOne(Session s, Long id) {
		return s.get(ClienteVO.class, id);
	}
}
