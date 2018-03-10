package in.co.mss.rmshib.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


 

public class HibDataSource {
	
	public static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("test");
		}
		return sessionFactory;

	}

	public static Session getSession() {

		Session session = getSessionFactory().openSession();
		return session;
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}


}
