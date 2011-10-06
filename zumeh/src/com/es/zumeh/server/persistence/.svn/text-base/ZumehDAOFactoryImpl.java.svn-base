package com.es.zumeh.server.persistence;

public class ZumehDAOFactoryImpl extends ZumehDAOFactory {

	@SuppressWarnings("unchecked")
	private GenericDAOImpl instantiateDAO(Class daoClass) {
		try {
			GenericDAOImpl newDao = (GenericDAOImpl) daoClass.newInstance();
			return newDao;
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass,
					ex);
		}
	}

	@Override
	public UserDAO getUserDAO() {
		return (UserDAO) instantiateDAO(UserDAO.class);
	}

}
