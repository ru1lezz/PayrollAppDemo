package com.sunggat.payroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.dao.DAOException;
import com.sunggat.payroll.entity.BaseEntity;

@Service
public abstract class AbstractService<T extends BaseEntity> implements CrudService<T>{

	@Autowired
	public CrudDAO<T> crudDAO;
	
	@Override
    @Transactional
    public T save(T entity) throws ServiceException {
        T focusEntity = null;
        try {
            if (entity.getId() != 0) {
                focusEntity = crudDAO.update(entity);
            } else {
                focusEntity = crudDAO.add(entity);
            }
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
        return focusEntity;
    }

    @Override
    @Transactional
    public void remove(T entity) throws ServiceException {
        try {
            crudDAO.delete(entity);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional
    public T get(int id) throws ServiceException {
        try {
            return crudDAO.get(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
	
	@Override
	@Transactional
    public List<T> getAll() throws ServiceException {
		try {
			return crudDAO.getAll();
		} catch (DAOException ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		
	}
	
}
