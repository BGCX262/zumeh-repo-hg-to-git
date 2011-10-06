package com.es.zumeh.server.persistence;

import com.es.zumeh.client.model.user.User;

/**
 * Defines all DAOs and the concrete factories to get the conrecte DAOs.
 * <p>
 * To get a concrete DAOFactory, call <tt>instance()</tt> with one of the
 * classes that extend this factory.
 * <p>
 * Implementation: If you write a new DAO, this class has to know about it.
 * If you add a new persistence mechanism, add an additional concrete factory
 * for it as a constant, like <tt>HIBERNATE</tt>.
 */
public abstract class ZumehDAOFactory {
	
	    /**
	     * Creates a standalone DAOFactory that returns unmanaged DAO
	     * beans for use in any environment Hibernate has been configured
	     * for. Uses HibernateUtil/SessionFactory and Hibernate context
	     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
	     * and transaction scoped.
	     */
	    private static Class<? extends ZumehDAOFactory>  defaultFactoryClass = ZumehDAOFactoryImpl.class;
	    
	    
	    public abstract UserDAO getUserDAO();
	    
	    public static void setDefaulFactoryClass(Class<? extends ZumehDAOFactory> newFactoryClass){
	        defaultFactoryClass = newFactoryClass;
	    }
	    
	    public static ZumehDAOFactory instance(){
	        return instance(defaultFactoryClass);
	    }
	    // as duas estao iguais por enquanto
	    // TODO entender a diferenca entre o shared e o default
	    public static ZumehDAOFactory sharedSessionFactory(){
	        return instance(ZumehDAOFactoryImpl.class);
	    }

	    /**
	     * Factory method for instantiation of concrete factories.
	     */
	    public static ZumehDAOFactory instance(Class<? extends ZumehDAOFactory> factory) {
	        try {
	            return (ZumehDAOFactory)factory.newInstance();
	        } catch (Exception ex) {
	            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
	        }
	    }

	    public static class UserDAO extends GenericDAOImpl<User, Long>{};
	    
}
