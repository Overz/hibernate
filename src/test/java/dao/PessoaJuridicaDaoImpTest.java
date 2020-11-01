package dao;

import static util.Generator.*;

import db.Connection;
import java.util.Collections;
import java.util.List;
import model.dao.daoi.PessoaJuridicaDAO;
import model.dao.impl.PessoaJuridicaDaoImp;
import model.vo.CartaoVO;
import model.vo.PessoaJuridicaVO;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class PessoaJuridicaDaoImpTest extends Assert implements BaseTEST {
	private static PessoaJuridicaDAO dao;
	private static PessoaJuridicaVO pj;
	private static Session s;

	public PessoaJuridicaDaoImpTest() {
		dao = new PessoaJuridicaDaoImp();
		s = Connection.getSession();
	}

	public void preencherObj() {
		List<PessoaJuridicaVO> l = dao.find(s);
		pj = l != null && !l.isEmpty() ? l.get(0) : null;

		if (pj == null) {
			criarObj();

			dao.save(s, pj);
			System.out.println(pj);
		}
	}

	public void criarObj() {
		CartaoVO c = new CartaoVO(
			generate(CARTAO),
			generate(NOME, 4),
			generate(ANO)
		);
		pj =
			PessoaJuridicaVO
				.builder()
				.setNome(generate(NOME))
				.setEmail(generate(EMAIL))
				.setCnpj(generate(CNPJ))
				.setInscricao(generate(INSCRICAO))
				.build();
		pj.setCartaoVO(Collections.singletonList(c));
		c.setClienteVO(pj);
	}

	@Test
	@Override
	public void cadastrar() {
		System.out.println(getClass().getSimpleName() + " Cadastrar");

		criarObj();
		Boolean res = dao.save(s, pj);

		assertTrue(res);
		assertNotNull(pj.getId());
		assertNotNull(pj.getCartaoVO().get(0).getId());
	}

	@Test
	@Override
	public void alterar() {
		System.out.println(getClass().getSimpleName() + " Alterar");

		preencherObj();

		String oldEmail = pj.getEmail();
		String oldName = pj.getNome();

		pj.setEmail(generate(EMAIL));
		pj.setNome(generate(NOME));

		Boolean res = dao.save(s, pj);

		preencherObj();

		assertTrue(res);
		assertNotEquals("Os emails devem ser diferentes", oldEmail, pj.getEmail());
		assertNotEquals("Os nomes devem ser diferentes", oldName, pj.getNome());
	}

	@Test
	@Override
	public void consultarNome() {
		System.out.println(getClass().getSimpleName() + " Consultar Nome");

		List<PessoaJuridicaVO> l = dao.find(s, "A");

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, pj);
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

		pj = dao.findOne(s, pj.getId());

		assertNotNull(pj);
		assertNotNull(pj.getId());
		assertNotNull(pj.getCartaoVO().get(0).getId());
	}

	@Test
	@Override
	public void listar() {
		System.out.println(getClass().getSimpleName() + " Listar");

		List<PessoaJuridicaVO> l = dao.find(s);

		if (l == null || l.isEmpty()) {
			criarObj();

			dao.save(s, pj);
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

		Boolean res = dao.delete(s, pj);

		assertTrue(res);
	}
}
