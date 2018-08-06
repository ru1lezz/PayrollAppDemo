package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.Performance;

@Component
public class PerformanceService extends AbstractService<Performance> {

	@Autowired
	public PerformanceService (CrudDAO<Performance> crudDAO) {
		this.crudDAO = crudDAO;
	}
	
}
