/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.tutorial.annotations;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;

/**
 * Illustrates the use of Hibernate native APIs.  The code here is unchanged from the {@code basic} example, the
 * only difference being the use of annotations to supply the metadata instead of Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class AnnotationsIllustrationTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			System.out.println(sessionFactory + "--------------sess");
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			System.out.println(e);
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public void testBasicUsage() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new Event( "Our very first event!", new Date() ) );
		session.save( new Event( "A follow up event", new Date() ) );
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Event" ).list();
		for ( Event event : (List<Event>) result ) {
			System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
		}
        session.getTransaction().commit();
        session.close();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void testUser() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new User("Ben", new Date() ) );
		session.save( new User("Wen", new Date() ) );
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery( "from User", User.class ).list();
        List<Event> events= session.createQuery( "from Event", Event.class ).list();
        
		for ( User user : users ) {
			System.out.println( "User (" + user.getDate() + ") : " + user.getName() );
		}
		User user = users.get(0);
		
		for (Event event : events) {
			event.setUser(user);
			session.save(event);
		}
        session.getTransaction().commit();
        session.close();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void testAssociation() {
		// create a couple of events...
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save( new Event( "Our very first event!", new Date() ) );
				session.save( new Event( "A follow up event", new Date() ) );
				session.getTransaction().commit();
				session.close();
				
				// create a couple of events...
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.save( new User("Ben", new Date() ) );
				session.save( new User("Wen", new Date() ) );
				session.getTransaction().commit();
				session.close();

				// now lets pull events from the database and list them
				session = sessionFactory.openSession();
		        session.beginTransaction();
		        List<User> users = session.createQuery( "from User", User.class ).list();
		        List<Event> events= session.createQuery( "from Event", Event.class ).list();
		        
				for ( User user : users ) {
					System.out.println( "User (" + user.getDate() + ") : " + user.getName() );
				}
				User user = users.get(0);
				
				for (Event event : events) {
					event.setUser(user);
					session.save(event);
				}
		        session.getTransaction().commit();
		        session.close();
		        
		        session = sessionFactory.openSession();
		        session.beginTransaction();
		        users = session.createQuery( "from User", User.class ).list();
		        
				for ( User user1 : users ) {
					System.out.println( "User (" + user1.getDate() + ") : " + user1.getName() );
					
					for (Event event : user1.getEvents()) {
						System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
					}
				}
				
		        session.getTransaction().commit();
		        session.close();
	}

}

