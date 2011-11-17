package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.es.zumeh.server.model.persistence.Work;

public class WorkDAO implements ObjectDAO<Work> {

	@Override
	public void save(Work work) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(work);
		
		session.flush();
		HibernateUtil.commitTransaction();
	}

	@Override
	public void delete(Work object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllObjects() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String hql = "delete from com.es.zumeh.server.model.persistence.Work ";
		Query query = session.createQuery(hql);
		
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount); //TODO optional
        
        HibernateUtil.commitTransaction();
		
	}

	@Override
	public void update(Work object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Work[] getAllObjects() {
		List<Work> realWorks = getAllWorks();
		Work[] works = new Work[realWorks.size()];
		
		for (int i=0; i < realWorks.size(); i++) {
			works[i] = realWorks.get(i);
		}
		
		return works;
	}
	
	@SuppressWarnings("unchecked")
	private List<Work> getAllWorks() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String query = "select n from com.es.zumeh.server.model.persistence.Work n";
		
		List<Work> works = new ArrayList<Work>(session.createQuery(query).list());
		
		return works;
	}

}
