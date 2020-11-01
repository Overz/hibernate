package model.dao.daoi;

import java.util.List;
import model.dao.BaseDAO;
import model.vo.ProfissaoVO;
import org.hibernate.Session;

public interface ProfissaoDAO extends BaseDAO<ProfissaoVO, Long> {
	List<ProfissaoVO> find(Session s, String value);

	List<ProfissaoVO> find(Session s);
}
