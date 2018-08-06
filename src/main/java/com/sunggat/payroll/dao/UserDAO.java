package com.sunggat.payroll.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunggat.payroll.entity.User;

@Repository
public class UserDAO extends AbstractDAO<User>{

	@Autowired
	private SessionFactory sessionFactory;

	public User findUserAccount(Integer id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}
	
}
