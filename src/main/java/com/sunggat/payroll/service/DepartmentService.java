package com.sunggat.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunggat.payroll.dao.CrudDAO;
import com.sunggat.payroll.entity.Department;

@Component
public class DepartmentService extends AbstractService<Department>{

	@Autowired
	public DepartmentService(CrudDAO<Department> crudDAO) {
		this.crudDAO = crudDAO;
	}	
	
}
