package com.es.zumeh.server.persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.persistence.User;

public class UserDAO {

	public void saveUser(User user) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		session.saveOrUpdate(user);
		
		session.flush();
		HibernateUtil.commitTransaction();
	}

	public User getUserByLogin(String login) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));

		User user = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();

		return user;
	}

	public boolean addUser(UserTO user) {
		
		User newUser = convertToRealUser(user);

		if (existsLogin(newUser.getLogin())
				|| existsEmail(newUser.getEmail())) { // FIXME add log
			return false;
		}
		saveUser(newUser);

		return true;
	}
	
	private User convertToRealUser(UserTO user) {
		User newUser = new User();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		newUser.setBirthday(user.getBirthday());
		newUser.setGender(user.getGender());
		newUser.setInterestedArea(user.getInterestedArea());
		newUser.setLocation(user.getLocation());
		newUser.setName(user.getName());
		newUser.setWhoAreYou(user.getWhoAreYou());
		return newUser;
	}
	
	private UserTO convertToTransferObjectUser(User user) {
		UserTO newUser = new UserTO();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		newUser.setBirthday(user.getBirthday());
		newUser.setGender(user.getGender());
		newUser.setInterestedArea(user.getInterestedArea());
		newUser.setLocation(user.getLocation());
		newUser.setName(user.getName());
		newUser.setWhoAreYou(user.getWhoAreYou());
		return newUser;
	}
	
	public boolean userExists(User user) {
		return (existsLogin(user.getLogin()) || existsEmail(user.getEmail()));
	}

	private boolean existsEmail(String email) {
		return getUserByEmail(email) != null;
	}
	
	public void deleteFromUsers() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String hql = "delete from com.es.zumeh.server.model.persistence.User ";
		Query query = session.createQuery(hql);
		
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        
        HibernateUtil.commitTransaction();
	}

	private User getUserByEmail(String email) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));

		User user = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();

		return user;

	}

	private boolean existsLogin(String login) {
		return getUserByLogin(login) != null;
	}

	public UserTO verifyUserTO(UserTO user) {
		
		User newUser = convertToRealUser(user);
		if(isUserChecked(newUser)) {
			return convertToTransferObjectUser(getUserByLogin(newUser.getLogin()));
		}
		return null;
		
	}

	private boolean isUserChecked(User newUser) {
		return (existsLogin(newUser.getLogin()) && existPassword(newUser.getPassword()));
	}

	private boolean existPassword(String password) {
		return getUserByPass(password) != null;
	}

	private User getUserByPass(String password) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("password", password));

		User user = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();

		return user;
	}

}
