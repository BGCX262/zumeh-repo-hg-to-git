package com.es.zumeh.server.model;

import com.es.zumeh.client.model.to.NodeTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.persistence.Comment;
import com.es.zumeh.server.model.persistence.Node;
import com.es.zumeh.server.model.persistence.Revision;
import com.es.zumeh.server.model.persistence.User;
import com.es.zumeh.server.model.persistence.Work;
import com.es.zumeh.server.persistence.CommentDAO;
import com.es.zumeh.server.persistence.HibernateUtil;
import com.es.zumeh.server.persistence.NodeDAO;
import com.es.zumeh.server.persistence.RevisionDAO;
import com.es.zumeh.server.persistence.UserDAO;
import com.es.zumeh.server.persistence.WorkDAO;

public class DAOManager {
	
	private UserDAO userDAO;
	
	private NodeDAO nodeDAO;
	private CommentDAO commentDAO;
	private RevisionDAO revisionDAO;
	private WorkDAO workDAO;
	
	private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
	
	public DAOManager() {
		HibernateUtil.setUp(HIBERNATE_CFG_XML);
		userDAO = new UserDAO();
		nodeDAO = new NodeDAO();
		revisionDAO = new RevisionDAO();
		workDAO = new WorkDAO();
		//setWorkDAO(new WorkDAO());
		setCommentDAO(new CommentDAO());
		
	}
	
	public boolean addUser(UserTO user) {
		boolean addUser = false;
		
		try {
			addUser = userDAO.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
		return addUser; 
	}
	
	public RevisionTO[] getRevisionsByOwner(String owner) {
		RevisionTO[] allRevisionsByOwner = {};
		try {
			allRevisionsByOwner = getRevisionDAO().getAllRevisionsTOByOwner(owner);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
		return allRevisionsByOwner; 
	}
	
	public void addNodeTO(NodeTO node) {
		try {
			nodeDAO.saveNodeTO(node);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}
	
	public void addNode(Node node) {
		try {
			nodeDAO.saveNode(node);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}
	
	public void addWork(Work work) {
		try {
			getWorkDAO().save(work);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
	}
	
	public Long addRevision(RevisionTO revisionTO) {
		try {
			return getRevisionDAO().saveRevision(revisionTO);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0L;
		
	}
	
	public RevisionDAO getRevisionDAO() {
		return revisionDAO;
	}

	public void setRevisionDAO(RevisionDAO revisionDAO) {
		this.revisionDAO = revisionDAO;
	}

	public void addComment(Comment comment) {
		try {
			getCommentDAO().save(comment);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void deleteAllUsers() {
		getUserDAO().deleteFromUsers();
	}
	
	public UserTO verifyUserTO(UserTO user) {
		return getUserDAO().verifyUserTO(user);
	}
	
	public boolean verifyUser(User user) {
		return getUserDAO().userExists(user);
	}
	
	public User getUser(String login) {
		return getUserDAO().getUserByLogin(login);
	}
	
	public UserTO getUserByEmail(String email) {
		User userByEmail = getUserDAO().getUserByEmail(email);
		if (userByEmail != null) {
			return getUserDAO().convertToTransferObjectUser(userByEmail);
		}
		
		return null;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public UserTO[] getAllUsers() {
		return userDAO.getAllUsers();
	}

	public void deleteUserData() {
		//getUserDAO().deleteUserData();
		
	}
	
	public boolean verifyUserByEmail(String email) {
		return getUserDAO().verifyUserByEmail(email);
	}

	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}

	public boolean verifyNode(Node node) {
		return getNodeDAO().verifyNode(node);
		
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public WorkDAO getWorkDAO() {
		return workDAO;
	}

	public void setWorkDAO(WorkDAO workDAO) {
		this.workDAO = workDAO;
	}

	public boolean deleteRevision(RevisionTO revision) {
		try {
			return getRevisionDAO().delete(revision.getRevisionId());
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}


}
