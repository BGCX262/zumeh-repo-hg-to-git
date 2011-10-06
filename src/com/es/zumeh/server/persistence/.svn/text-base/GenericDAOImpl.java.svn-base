package com.es.zumeh.server.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 * Implements the generic CRUD data access operations using Hibernate APIs.
 * <p>
 * To write a DAO, subclass and parameterize this class with your persistent
 * class. Of course, assuming that you have a traditional 1:1 approach for
 * Entity:DAO design.
 * <p>
 * You have to inject a current Hibernate <tt>Session</tt> to use a DAO.
 * Otherwise, this generic implementation will use
 * <tt>HibernateUtil.getSessionFactory()</tt> to obtain the current
 * <tt>Session</tt>.
 * 
 */
public class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private Class<T> persistentClass;
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	public GenericDAOImpl(Class<T> clazz) {
		this.persistentClass = clazz;
	}

	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null)
			this.setSession(HibernateUtil.getSession());
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findByPK(ID id) {
		Transaction transaction = this.getSession().beginTransaction();
		T entity = (T) getSession().get(getPersistentClass(), id);
		transaction.commit();
		return entity;
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Transaction transaction = this.getSession().beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		List list = crit.list();
		transaction.commit();
		return list;
	}

	public List<T> findByQuery(String query) {

		return findByCriteria(ConsultaParser.parseConsulta(query));
	}

	@SuppressWarnings("unchecked")
	public T findUnique(T exampleInstance, String... excludeProperty) {
		Transaction transaction = this.getSession().beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		T uniqueResult = (T) crit.uniqueResult();
		transaction.commit();
		return uniqueResult;
	}

	public T makePersistent(T... entity) {
		return makePersistent(false, entity);
	}

	public T makePersistent(boolean autoClose, T... entity) {
		if (entity.length <= 0)
			return null;// do nothing

		Transaction transaction = this.getSession().beginTransaction();
		try {
			for (T t : entity) {
				getSession().saveOrUpdate(t);
			}
			transaction.commit();
			if (autoClose) {
				this.close();
			}
			return entity[0];
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			this.close();
			throw e;
		}
	}

	public void makeTransient(T... entity) {
		makeTransient(false, entity);
	}

	public void makeTransient(boolean autoClose, T... entity) {
		if (entity.length <= 0)
			return;

		Transaction transaction = this.getSession().beginTransaction();
		try {
			for (T t : entity) {
				getSession().delete(t);
			}
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			if (autoClose)
				this.close();
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void close() {
		if (this.session != null) {
			this.session.close();
			this.session = null;
		}
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Transaction transaction = this.getSession().beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		List list = crit.list();
		transaction.commit();
		return list;
	}

	protected void deleteAll() {
		deleteAll(false);
	}

	@SuppressWarnings("unchecked")
	protected void deleteAll(boolean autoCloseSession) {
		List<T> l = findAll();
		makeTransient(autoCloseSession, (T[]) l.toArray());
	}

}
