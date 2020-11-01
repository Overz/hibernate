package db;

import javax.persistence.metamodel.EntityType;
import model.vo.*;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class Connection {
	private static final SessionFactory ourSessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			Connection.annotated(configuration);
			// Caminho "absoluto" para o arquivo de configuração do hivernate
			configuration.configure("/META-INF/hibernate.cfg.xml");

			// Deixa as consultas em memoria, para não precisar consultar 2x as mesmas coisas
			ServiceRegistry service = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

			// Cria a configuração pelas instancias em memoria
			ourSessionFactory = configuration.buildSessionFactory(service);
		} catch (Throwable ex) {
			System.out.println(ex.getClass().getSimpleName());
			System.err.println(ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static void annotated(Configuration config) {
		config.addAnnotatedClass(ClienteVO.class);
		config.addAnnotatedClass(CartaoVO.class);
		config.addAnnotatedClass(PessoaFisicaVO.class);
		config.addAnnotatedClass(PessoaJuridicaVO.class);
		config.addAnnotatedClass(ProfissaoVO.class);
	}

	public static Session getSession() throws HibernateException {
		try {
			return ourSessionFactory.openSession();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass().getSimpleName());
		}
		return null;
	}

	public static void main(final String[] args) {
		try (Session session = getSession()) {
			System.out.println("querying all the managed entities...");
			final Metamodel metamodel = session.getSessionFactory().getMetamodel();
			for (EntityType<?> entityType : metamodel.getEntities()) {
				final String entityName = entityType.getName();
				final Query query = session.createQuery("from " + entityName);
				System.out.println("executing: " + query.getQueryString());
				for (Object o : query.list()) {
					System.out.println("  " + o);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
	}
}
