package com.es.zumeh.server.persistence;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<DTO, ID extends Serializable> {

	/**
	 * Retrieves DTO with the specified PK.
	 * 
	 * @param id
	 * @return
	 */
	public DTO findByPK(ID id);

	/**
	 * Get all DTOs from DB.
	 * 
	 * @return
	 */
	public List<DTO> findAll();

	/**
	 * Retrieve multple DTOs from DB
	 * 
	 * @param exampleInstance
	 * @param excludeProperty
	 * @return
	 */
	public List<DTO> findByExample(DTO exampleInstance,
			String... excludeProperty);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<DTO> findByQuery(String query);

	/**
	 * Single transaction to persist multiples DTOs
	 * 
	 * @param entity
	 * @return
	 */
	public DTO makePersistent(DTO... entity);

	/**
	 * Single transaction make multiples DTOs transient
	 * 
	 * @param entity
	 * @return
	 */
	public void makeTransient(DTO... entity);

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	public void flush();

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	public void clear();

	/**
	 * Closes current session.
	 */
	public void close();

}
