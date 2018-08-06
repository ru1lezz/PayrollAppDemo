package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.UserDetails;

@Component
public class UserDetailService extends AbstractService<UserDetails> {

	@Autowired
	public UserDetailService (CrudDAO<UserDetails> crudDAO) {
		this.crudDAO = crudDAO;
	}
	
}
