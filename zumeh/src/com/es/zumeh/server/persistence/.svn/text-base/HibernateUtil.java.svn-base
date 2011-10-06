package com.es.zumeh.server.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.es.zumeh.server.model.User;

/**
 * Classe utilitaria que levanta o hibernate. HIBERNATE_CFG_XML define as
 * configuracoes, valores default para um postgres com um DB de nome 'zumeh',
 * usuario de nome 'root' e e senha '':
 * 
 */
public class HibernateUtil {

	private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
	private static final SessionFactory sessionFactory;
	static {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration()
					.addResource(HIBERNATE_CFG_XML).addAnnotatedClass(User.class);
			sessionFactory = cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
}