package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.es.zumeh.server.model.persistence.Revision;

public class RevisionDAO implements ObjectDAO<Revision> {

	@Override
	public void save(Revision revision) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(revision);
		
		session.flush();
		HibernateUtil.commitTransaction();
	}

	@Override
	public void delete(Revision revision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllObjects() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String hql = "delete from com.es.zumeh.server.model.persistence.Revision ";
		Query query = session.createQuery(hql);
		
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount); //TODO optional
        
        HibernateUtil.commitTransaction();
		
	}

	@Override
	public void update(Revision revision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Revision[] getAllObjects() {
		List<Revision> realRevisions = getAllRevisions();
		Revision[] nodes = new Revision[realRevisions.size()];
		
		for (int i=0; i < realRevisions.size(); i++) {
			nodes[i] = realRevisions.get(i);
		}
		
		return nodes;
	}
	
	@SuppressWarnings("unchecked")
	private List<Revision> getAllRevisions() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String query = "select n from com.es.zumeh.server.model.persistence.Revision n";
		
		List<Revision> revisions = new ArrayList<Revision>(session.createQuery(query).list());
		
		return revisions;
	}

}
