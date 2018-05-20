package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.Job;

@Component
public class JobService extends AbstractService<Job> {

	@Autowired
	public JobService (CrudDAO<Job> crudDAO) {
		this.crudDAO = crudDAO;
	}
	
}
