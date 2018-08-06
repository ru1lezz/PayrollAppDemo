package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.Benefit;

@Component
public class BenefitService extends AbstractService<Benefit> {

	@Autowired
	public BenefitService(CrudDAO<Benefit> crudDAO) {
		this.crudDAO = crudDAO;
	}
}
