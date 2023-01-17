package jpa.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static Session connection() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session connectionSession = factory.openSession();
		
		return connectionSession;
		
	}

}
