package model.dao.daoi;

import java.util.List;
import model.dao.BaseDAO;
import model.vo.CartaoVO;
import org.hibernate.Session;

public interface CartaoDAO extends BaseDAO<CartaoVO, Long> {
	List<CartaoVO> find(Session s, String value);

	List<CartaoVO> find(Session s);
}
