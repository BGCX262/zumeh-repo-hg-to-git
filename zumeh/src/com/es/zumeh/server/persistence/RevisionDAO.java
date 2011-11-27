package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.server.model.persistence.Revision;

public class RevisionDAO implements ObjectDAO<Revision> {

	@Override
	public void save(Revision revision) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		System.out.println("ID DO MISERAVI = " + revision.getRevisionId());
		
		if (revision.getRevisionId() == 0) {
			revision.setRevisionId(null);
			session.saveOrUpdate(revision);
		} else {
			session.update(revision);
		}
		
		session.flush();
		HibernateUtil.commitTransaction();
	}
	
	public boolean saveRevision(RevisionTO revisionTO) {
		Revision revision = convertRevisionTO(revisionTO);
		save(revision);
		return true; //XXX verify later
	}

	private Revision convertRevisionTO(RevisionTO revisionTO) {
		Revision revision = new Revision();
		revision.setComments(revisionTO.getComments());
		revision.setFullDescriptionText(revisionTO.getFullDescriptionText());
		revision.setShortDescriptionText(revisionTO.getShortDescriptionText());
		revision.setWorks(revisionTO.getWorks());
		revision.setRevisionOwner(revisionTO.getRevisonOwner());
		revision.setRevisionId(revisionTO.getRevisionId());
		return revision;
	}
	
	private RevisionTO convertTO2Revision(Revision revision) {
		RevisionTO revisionTO = new RevisionTO();
		revisionTO.setComments(revision.getComments());
		revisionTO.setFullDescriptionText(revision.getFullDescriptionText());
		revisionTO.setShortDescriptionText(revision.getShortDescriptionText());
		revisionTO.setWorks(revision.getWorks());
		revisionTO.setRevisionId(revision.getRevisionId());
		revisionTO.setRevisionOwner(revision.getRevisionOwner());
		return revisionTO;
	}
	
//	public Revision getRevisionByOwner(String owner) {
//		Session session = HibernateUtil.getSession();
//		HibernateUtil.beginTransaction();
//		Criteria criteria = session.createCriteria(Revision.class);
//		criteria.add(Restrictions.eq("revisionOwner", owner));
//
//		Revision revision = (Revision) criteria.list();
//		HibernateUtil.commitTransaction();
//		return revision;
//	}

	@Override
	public void delete(Revision revision) {
	
	}
	
	public Revision getRevisionByRevisionID(Long id) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Revision.class);
		criteria.add(Restrictions.eq("revisionId", id));

		Revision revision = (Revision) criteria.uniqueResult();
		HibernateUtil.commitTransaction();
		return revision;
	}
		
	
	public boolean delete(Long id) {
		Revision revisionByRevisionID = getRevisionByRevisionID(id);
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.delete(revisionByRevisionID);
		session.flush();
		HibernateUtil.commitTransaction();
		return true;
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
	
	public RevisionTO[] getAllRevisionsTOByOwner(String owner) {
		Revision[] allRevisionsByOwner = getAllRevisionsByOwner(owner);
		RevisionTO[] allRevisionTOs = new RevisionTO[allRevisionsByOwner.length];
		
		for (int i = 0; i < allRevisionsByOwner.length; i++) {
			allRevisionTOs[i] = convertTO2Revision(allRevisionsByOwner[i]);
		}
		return allRevisionTOs;
	}
	
	public Revision[] getAllRevisionsByOwner(String owner) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Revision.class);
		criteria.add(Restrictions.eq("revisionOwner", owner));

		@SuppressWarnings("unchecked")
		List<Revision> revision = criteria.list();
		HibernateUtil.commitTransaction();
		
		Revision[] revisionArray = getRevisionArray(revision);
		return revisionArray;
	}

	private Revision[] getRevisionArray(List<Revision> revision) {
		Revision[] result = new Revision[revision.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = revision.get(i);
		}
		
		return result;
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
