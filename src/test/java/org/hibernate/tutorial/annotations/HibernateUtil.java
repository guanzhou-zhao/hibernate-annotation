package org.hibernate.tutorial.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata()
					.buildSessionFactory();
			System.out.println(sessionFactory + "--------------sess");
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had
			// trouble building the SessionFactory
			// so destroy it manually.
			System.out.println(e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
