package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.User;

@Component
public class UserService extends AbstractService<User>{

	@Autowired
	public UserService (CrudDAO<User> crudDAO) {
		this.crudDAO = crudDAO;
	}
	
}
