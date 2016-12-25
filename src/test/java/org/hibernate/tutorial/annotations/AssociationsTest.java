package org.hibernate.tutorial.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AssociationsTest {

	private SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had
			// trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
			throw new ExceptionInInitializerError(e);
		}
	}

	@After
	public void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@Test
	public void test() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Company wendy = new Company("Wendy");
		CompanyDetail detail = new CompanyDetail("88 disraeli st");

		wendy.addDetail(detail);
		session.save(detail);
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		Company c1 = session.byId(Company.class).load(1L);
		System.out.println(c1);
		session.close();
		session = sessionFactory.openSession();
		CompanyDetail cd1 = session.byId(CompanyDetail.class).load(2L);
		System.out.println(cd1);
		session.close();
	}

}
