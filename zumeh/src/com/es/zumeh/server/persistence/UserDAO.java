package com.es.zumeh.server.persistence;

import java.util.ArrayList;
import java.util.List;

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

		if (verifyLoginAndEmail(newUser)) { // FIXME add log
			return false;
		}
		saveUser(newUser);

		return true;
	}

	private boolean verifyLoginAndEmail(User newUser) {
		return existsLogin(newUser.getLogin())
				|| existsEmail(newUser.getEmail());
	}
	
	public boolean verifyUserByEmail(String email) {
		return existsEmail(email);
	}
	
	public boolean userExists(User user) {
		return verifyLoginAndEmail(user);
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
	
	@SuppressWarnings("unchecked")
	private List<User> getAllRealUsers() {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		
		String query = "select u from com.es.zumeh.server.model.persistence.User u";
		List<User> users = new ArrayList<User>(session.createQuery(query).list());
		users.size();
		
		HibernateUtil.closeSession();
		return users;
	}
	
	public UserTO[] getAllUsers() {
		List<User> realUsers = getAllRealUsers();
		UserTO[] users = new UserTO[realUsers.size()];
		
		for (int i=0; i < realUsers.size(); i++) {
			users[i] = convertToTransferObjectUser(realUsers.get(i));
			//users.add(convertToTransferObjectUser(user));
		}
		
		return users;
	}


	public UserTO verifyUserTO(UserTO user) {
		
		User newUser = convertToRealUser(user);
		if(isUserChecked(newUser)) {
			return convertToTransferObjectUser(getUserByLogin(newUser.getLogin()));
		}
		return null;
		
	}

	private boolean existsEmail(String email) {
		return getUserByEmail(email) != null;
	}

	private boolean existsLogin(String login) {
		return getUserByLogin(login) != null;
	}

	private boolean isUserChecked(User newUser) {
		return (existsLogin(newUser.getLogin()) && existPassword(newUser.getPassword()));
	}

	private boolean existPassword(String password) {
		return getUserByPass(password) != null;
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
		//newUser.setImage(user.getImage());
		return newUser;
	}

	public UserTO convertToTransferObjectUser(User user) {
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
		//newUser.setImage(user.getImage());
		return newUser;
	}

	public User getUserByEmail(String email) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
	
		User user = (User) criteria.uniqueResult();
		HibernateUtil.commitTransaction();
	
		return user;
	
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
