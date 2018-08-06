package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.Payroll;

@Component
public class PayrollService extends AbstractService<Payroll>{

	@Autowired
	public PayrollService(CrudDAO<Payroll> crudDAO) {
		this.crudDAO = crudDAO;
	}
	
}
