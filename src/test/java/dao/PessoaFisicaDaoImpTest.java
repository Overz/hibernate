package dao;

import static util.Generator.*;

import db.Connection;
import java.util.Collections;
import java.util.List;
import model.dao.daoi.PessoaFisicaDAO;
import model.dao.impl.PessoaFisicaDaoImp;
import model.vo.CartaoVO;
import model.vo.PessoaFisicaVO;
import model.vo.ProfissaoVO;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class PessoaFisicaDaoImpTest extends Assert implements BaseTEST {
	private static PessoaFisicaDAO dao;
	private static PessoaFisicaVO pf;
	private static Session s;

	public PessoaFisicaDaoImpTest() {
		dao = new PessoaFisicaDaoImp();
		s = Connection.getSession();
	}

	public void preencherObj() {
		List<PessoaFisicaVO> l = dao.find(s);
		pf = l != null && !l.isEmpty() ? l.get(0) : null;

		if (pf == null) {
			criarObj();

			dao.save(s, pf);
			pf = dao.find(s).get(0);
		}
	}

	public void criarObj() {
		CartaoVO c = new CartaoVO(
			generate(CARTAO),
			generate(NOME, 4),
			generate(ANO)
		);
		ProfissaoVO p = new ProfissaoVO(generate(NOME), generate(NOME, 50));
		pf =
			PessoaFisicaVO
				.builder()
				.setNome(generate(NOME))
				.setEmail(generate(EMAIL))
				.setCpf(generate(CPF))
				.setRg(generate(RG))
				.build();
		pf.setCartaoVO(Collections.singletonList(c));
		pf.setProfissaoVO(p);
		c.setClienteVO(pf);
	}

	@Test
	@Override
	public void cadastrar() {
		System.out.println(getClass().getSimpleName() + " Cadastrar");

		criarObj();
		Boolean res = dao.save(s, pf);

		assertTrue(res);
		assertNotNull(pf.getId());
		assertNotNull(pf.getCartaoVO().get(0).getId());
		assertNotNull(pf.getProfissaoVO().getId());
	}

	@Test
	@Override
	public void alterar() {
		System.out.println(getClass().getSimpleName() + " Alterar");

		preencherObj();

		String oldEmail = pf.getEmail();
		String oldName = pf.getNome();

		pf.setEmail(generate(EMAIL));
		pf.setNome(generate(NOME));

		Boolean res = dao.save(s, pf);

		preencherObj();

		assertTrue("Deve retornar True caso a alteração seja OK", res);
		assertNotEquals("Os emails devem ser diferentes", oldEmail, pf.getEmail());
		assertNotEquals("Os nomes devem ser diferentes", oldName, pf.getNome());
	}

	@Test
	@Override
	public void consultarNome() {
		System.out.println(getClass().getSimpleName() + " Consultar Nome");

		List<PessoaFisicaVO> l = dao.find(s, "A");

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, pf);
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

		pf = dao.findOne(s, pf.getId());

		assertNotNull(pf);
		assertNotNull(pf.getId());
		assertNotNull(pf.getCartaoVO().get(0).getId());
	}

	@Test
	@Override
	public void listar() {
		System.out.println(getClass().getSimpleName() + " Listar");

		List<PessoaFisicaVO> l = dao.find(s);

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, pf);
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

		Boolean res = dao.delete(s, pf);

		assertTrue(res);
	}
}
