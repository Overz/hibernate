package model.dao.daoi;

import java.util.List;
import model.dao.BaseDAO;
import model.vo.PessoaFisicaVO;
import org.hibernate.Session;

public interface PessoaFisicaDAO extends BaseDAO<PessoaFisicaVO, Long> {
	List<PessoaFisicaVO> find(Session s, String value);

	List<PessoaFisicaVO> find(Session s);
}
