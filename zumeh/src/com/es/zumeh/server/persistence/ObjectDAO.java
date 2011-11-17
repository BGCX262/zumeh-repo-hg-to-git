package com.es.zumeh.server.persistence;

public interface ObjectDAO<T> {
	
	public void save(T object);
	
	public void delete(T object);
	
	public void deleteAllObjects();
	
	public void update(T object);
	
	public T[] getAllObjects();
	
}
