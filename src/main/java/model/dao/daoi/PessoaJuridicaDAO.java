package model.dao.daoi;

import java.util.List;
import model.dao.BaseDAO;
import model.vo.PessoaJuridicaVO;
import org.hibernate.Session;

public interface PessoaJuridicaDAO extends BaseDAO<PessoaJuridicaVO, Long> {
	List<PessoaJuridicaVO> find(Session s, String value);

	List<PessoaJuridicaVO> find(Session s);
}
