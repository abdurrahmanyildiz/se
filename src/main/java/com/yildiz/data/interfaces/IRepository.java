package com.yildiz.data.interfaces;

import java.util.List;

public interface IRepository<T> {
	
	void add(T entity);
	
	void add(Iterable<T> entities);
	
	void remove(T entity);
		
	void update(T entity);
	
	T getById(Integer Id);
	
	List<T> GetAll();

}
