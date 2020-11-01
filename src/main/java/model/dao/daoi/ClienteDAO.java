package model.dao.daoi;

import java.util.List;
import model.dao.BaseDAO;
import model.vo.ClienteVO;
import org.hibernate.Session;

public interface ClienteDAO extends BaseDAO<ClienteVO, Long> {
	List<ClienteVO> find(Session s, String value);

	List<ClienteVO> find(Session s);
}
