package com.sunggat.payroll.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import com.sunggat.payroll.entity.BaseEntity;

@Repository
public abstract class AbstractDAO <T extends BaseEntity> implements CrudDAO<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> entityType;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
        this.entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractDAO.class);
    }
	
	 @Override
	    public T add(T entity) throws DAOException {
	        try {
	        	EntityManager em = sessionFactory.createEntityManager();
	        	EntityTransaction t = em.getTransaction();
	        	t.begin();
	        	em.merge(entity);
	        	t.commit();
	        } catch (Exception e) {
	            throw new DAOException("exception in DAO add", e);
	    }
	        return null;
	 }

	    @Override
	    public T update(T entity) throws DAOException {
	        T value = null;
	        try {
	        	EntityManager em = sessionFactory.createEntityManager();
	        	EntityTransaction t = em.getTransaction();
	        	t.begin();
	        	value = em.merge(entity);
	        	t.commit();
	        } catch (Exception e) {
	            throw new DAOException("exception in DAO update", e);
	        }
	        return value;
	    }

	    @Override
	    public void delete(T entity) throws DAOException {
	        try {
	        	EntityManager em = sessionFactory.createEntityManager();
	        	EntityTransaction t = em.getTransaction();
	        	t.begin();
	        	em.remove(entity);
	        	t.commit();
	        } catch (Exception e) {
	            throw new DAOException("exception in DAO delete", e);
	        }
	    }

	    @Override
	    public T get(int id) throws DAOException {
	        T value = null;
	        try {
	            value = sessionFactory.createEntityManager().find(entityType, id);
	        } catch (Exception e) {
	            throw new DAOException("exception in DAO get", e);
	        }
	        return value;
	    }
	
	@Override
    public List<T> getAll() throws DAOException {
        List<T> value = null;
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> select = cb.createQuery(entityType);
        Root<T> e = select.from(entityType);
        value = sessionFactory.createEntityManager().createQuery(select.select(e)).getResultList();
        return value;
    }
	
	
}
