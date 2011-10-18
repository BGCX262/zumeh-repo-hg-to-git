package com.es.zumeh.server.persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.es.zumeh.server.model.persistence.User;

public class UserDAO {

	public void saveUser(String login, String password, String email,
			String name, String whoAreYou, String interestedArea,
			String gender, String location, String birthday) {

		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
		user.setBirthday(birthday);
		user.setGender(gender);
		user.setInterestedArea(interestedArea);
		user.setLocation(location);
		user.setName(name);
		user.setWhoAreYou(whoAreYou);

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

	public boolean addUser(String login, String password, String email,
			String name, String whoAreYou, String interestedArea,
			String gender, String location, String birthday) {

		if (!existsLogin(login)) { // FIXME add log
			return false;
		}

		if (!existsEmail(email)) { // FIXME add log
			return false;
		}

		saveUser(login, password, email, name, whoAreYou, interestedArea,
				gender, location, birthday);

		return true;
	}

	private boolean existsEmail(String email) {
		return getUserByEmail(email) != null;
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

}
