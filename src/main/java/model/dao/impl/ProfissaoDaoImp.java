package model.dao.impl;

import java.util.List;
import model.dao.BaseDAOImp;
import model.dao.daoi.ProfissaoDAO;
import model.vo.ProfissaoVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProfissaoDaoImp
	extends BaseDAOImp<ProfissaoVO, Long>
	implements ProfissaoDAO {

	@Override
	public List<ProfissaoVO> find(Session s, String value) {
		Query<ProfissaoVO> qry = s.createQuery(
			"FROM ProfissaoVO WHERE nome LIKE :nome",
			ProfissaoVO.class
		);
		qry.setParameter("nome", like(value));
		return qry.list();
	}

	@Override
	public List<ProfissaoVO> find(Session s) {
		return s.createQuery("FROM ProfissaoVO", ProfissaoVO.class).list();
	}

	@Override
	public ProfissaoVO findOne(Session s, Long id) {
		return s.get(ProfissaoVO.class, id);
	}
}
