package com.sunggat.payroll.dao;

import java.util.List;

import com.sunggat.payroll.entity.BaseEntity;

public interface CrudDAO <T extends BaseEntity>{

	T add(T entity) throws DAOException;

    T update(T entity) throws DAOException;

    void delete(T entity) throws DAOException;

    T get(int id) throws DAOException; 
	
	public List<T> getAll() throws DAOException;

}
