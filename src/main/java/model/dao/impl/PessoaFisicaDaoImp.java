package model.dao.impl;

import java.util.List;
import model.dao.BaseDAOImp;
import model.dao.daoi.PessoaFisicaDAO;
import model.vo.PessoaFisicaVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PessoaFisicaDaoImp
	extends BaseDAOImp<PessoaFisicaVO, Long>
	implements PessoaFisicaDAO {

	@Override
	public List<PessoaFisicaVO> find(Session s, String value) {
		Query<PessoaFisicaVO> qry = s.createQuery(
			"FROM PessoaFisicaVO FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			PessoaFisicaVO.class
		);

		qry.setParameter("nome", like(value).toUpperCase());

		return qry.list();
	}

	@Override
	public List<PessoaFisicaVO> find(Session s) {
		return s.createQuery("FROM PessoaFisicaVO", PessoaFisicaVO.class).list();
	}

	@Override
	public PessoaFisicaVO findOne(Session s, Long id) {
		return s.get(PessoaFisicaVO.class, id);
	}
}
