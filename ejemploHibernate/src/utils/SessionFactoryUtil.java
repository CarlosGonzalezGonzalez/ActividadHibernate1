package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class SessionFactoryUtil {
	private static final SessionFactory sessionFactory;
	static{
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Ha fallado la creacion de SessionFactory: " + ex);
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
