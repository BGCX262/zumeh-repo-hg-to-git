package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.es.zumeh.server.model.persistence.Comment;

public class CommentDAO implements ObjectDAO<Comment> {

	@Override
	public void save(Comment comment) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(comment);
		
		session.flush();
		HibernateUtil.commitTransaction();
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllObjects() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String hql = "delete from com.es.zumeh.server.model.persistence.Comment ";
		Query query = session.createQuery(hql);
		
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        
        HibernateUtil.commitTransaction();
	}

	@Override
	public void update(Comment object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment[] getAllObjects() {
		List<Comment> realComments = getAllRealComments();
		Comment[] comments = new Comment[realComments.size()];
		
		for (int i=0; i < realComments.size(); i++) {
			comments[i] = realComments.get(i);
		}
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	private List<Comment> getAllRealComments() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String query = "select comm from com.es.zumeh.server.model.persistence.Comment comm";
		List<Comment> comments = new ArrayList<Comment>(session.createQuery(query).list());
		comments.size();
		
		HibernateUtil.closeSession();
		return comments;
	}

}
