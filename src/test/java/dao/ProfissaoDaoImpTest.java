package dao;

import db.Connection;
import java.util.List;
import model.dao.daoi.ProfissaoDAO;
import model.dao.impl.ProfissaoDaoImp;
import model.vo.PessoaFisicaVO;
import model.vo.ProfissaoVO;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import static util.Generator.*;

public class ProfissaoDaoImpTest extends Assert implements BaseTEST {

	private static ProfissaoDAO dao;
	private static ProfissaoVO p;
	private static Session s;

	public ProfissaoDaoImpTest() {
		dao = new ProfissaoDaoImp();
		s = Connection.getSession();
	}

	public void preencherObj(){
		List<ProfissaoVO> l = dao.find(s);
		p = l != null && !l.isEmpty() ? l.get(0) : null;

		if (p == null) {
			criarObj();

			dao.save(s, p);
			p = dao.find(s).get(0);
		}
	}

	public void criarObj(){
		p = new ProfissaoVO(generate(NOME), generate(NOME, 20));
	}

	@Test
	@Override
	public void cadastrar() {
		System.out.println(getClass().getSimpleName() + " Cadastrar");

		criarObj();
		Boolean res = dao.save(s, p);

		assertTrue(res);
		assertNotNull(p.getId());
	}

	@Test
	@Override
	public void alterar() {
		System.out.println(getClass().getSimpleName() + " Alterar");

		preencherObj();

		String oldName = p.getNome();
		String oldDescricao = p.getDescricao();

		p.setNome(generate(NOME));
		p.setDescricao(generate(NOME, 50));

		Boolean res = dao.save(s, p);

		preencherObj();

		assertTrue("Deve retornar True caso a alteração seja OK",res);
		assertNotEquals("As descrições devem ser diferentes", oldDescricao, p.getDescricao());
		assertNotEquals("Os nomes devem ser diferentes", oldName, p.getNome());

	}

	@Test
	@Override
	public void consultarNome() {
		System.out.println(getClass().getSimpleName() + " Consultar Nome");

		List<ProfissaoVO> l = dao.find(s, "A");

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, p);
			l = dao.find(s, "a");
		}

		assertNotNull(l);
		assertTrue(l.size() > 0);
		assertNotNull(l.get(0));
	}

	@Test
	@Override
	public void consultarId() {
		System.out.println(getClass().getSimpleName() + " Consultar Por ID");

		preencherObj();

		p = dao.findOne(s, p.getId());

		assertNotNull(p);
		assertNotNull(p.getId());
	}

	@Test
	@Override
	public void listar() {
		System.out.println(getClass().getSimpleName() + " Listar");

		List<ProfissaoVO> l = dao.find(s);

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, p);
			l = dao.find(s);
		}

		assertNotNull(l);
		assertTrue(l.size() > 0);
		assertNotNull(l.get(0));
	}

	@Test
	@Override
	public void excluir() {
		System.out.println(getClass().getSimpleName() + " Excluir");

		preencherObj();

		Boolean res = dao.delete(s, p);

		assertTrue(res);
	}
}
