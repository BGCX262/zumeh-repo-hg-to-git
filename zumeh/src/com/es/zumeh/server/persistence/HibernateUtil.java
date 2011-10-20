package com.es.zumeh.server.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Classe utilitaria que levanta o hibernate. HIBERNATE_CFG_XML define as
 * configuracoes, valores default para um postgres com um DB de nome 'zumeh',
 * usuario de nome 'root' e e senha '':
 * 
 */
public class HibernateUtil {
	
	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	public static void setUp(String confXMLPath) {
		try {
			configuration = new AnnotationConfiguration();
			sessionFactory = configuration.configure(confXMLPath).buildSessionFactory();
		} catch (Throwable ex) {
			//logger.error(ex);
			throw new ExceptionInInitializerError(ex);
			//ex.printStackTrace();
		}
	}
	
	public static void beginTransaction() {
		Transaction tx = (Transaction) sessionFactory.getCurrentSession().beginTransaction();
		try {
			if (tx == null) {
				//logger.trace("Starting new database transaction in this thread.");
				tx = getSession().beginTransaction();
				//threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			System.err.println("beginTransaction" + ex.getMessage());
		}
	}
	
	public static void commitTransaction() {
		Transaction tx = (Transaction) sessionFactory.getCurrentSession().getTransaction();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				//logger.trace("Committing database transaction of this thread.");
				tx.commit();
			}
			//threadTransaction.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			//logger.error("commitTransaction", ex);
		} finally {
			closeSession();
		}
	}
	
	public static void rollbackTransaction() {
		Transaction tx = (Transaction) sessionFactory.getCurrentSession().getTransaction();
		try {
			//threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				//logger.trace("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		} catch (HibernateException ex) {
			//logger.error("rollbackTransaction", ex);
		}
	}
	
	public static void closeSession() {
		try {
			Session session = (Session) sessionFactory.getCurrentSession();
			//threadSession.set(null);
			if (session != null && session.isOpen()) {
				//logger.trace("Closing Session of this thread.");
				session.close();
			}
		} catch (HibernateException ex) {
			///logger.error("closeSession", ex);
		}
	}
	
	public static void rollbackAndCloseSession() {
		rollbackTransaction();
		closeSession();
	}

	public static Session getSession() {
		Session session = (Session) sessionFactory.getCurrentSession();
		try {
			if (session == null || !session.isOpen()) {
				//logger.debug("Opening new Session for this thread.");
				session = getSessionFactory().openSession();
				//threadSession.set(session);
			}
		} catch (HibernateException ex) {
			//logger.error("getSession", ex);
		}
		return session;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public static Configuration getConfiguration() {
		return configuration;
	}

	public static Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
}