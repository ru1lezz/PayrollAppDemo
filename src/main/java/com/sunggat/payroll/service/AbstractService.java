package com.sunggat.payroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.dao.DAOException;
import com.sunggat.payroll.entity.BaseEntity;
import com.sunggat.payroll.entity.User;

@Service
public abstract class AbstractService<T extends BaseEntity> implements CrudService<T> {

	@Autowired
	public CrudDAO<T> crudDAO;
	
	@Override
    public T save(T entity) throws ServiceException {
        try {
            if (entity.getId() != 0) {
                crudDAO.update(entity);
            } else {
                crudDAO.add(entity);
            }
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
        return null;
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
