package com.sunggat.payroll.service;

import java.util.List;

import com.sunggat.payroll.entity.BaseEntity;

public interface CrudService<T extends BaseEntity> {

	T save(T entity) throws ServiceException;

    void remove(T entity) throws ServiceException;

    T get(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;
}
