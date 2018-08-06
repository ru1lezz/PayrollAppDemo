
package com.sunggat.payroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Department;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	private CrudService <Department> departmentService;
	
	@Autowired
	public DepartmentController(CrudService<Department> departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/list")
	public String getDepartments (Model theModel) {
		
		List<Department> departments = departmentService.getAll();
		theModel.addAttribute("departments", departments);
		
		return "departments/list";
	}
	
	@PostMapping("/saveDepartment")
	public String saveDepartment(@ModelAttribute("department") Department department) {
		
		departmentService.save(department);
		
		return "redirect:/department/list";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		Department department = departmentService.get(id);
		theModel.addAttribute("department", department);
		
		return "departments/departmentForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Department department = new Department();
		theModel.addAttribute("department", department);
		
		return "departments/departmentForm";
		
	}
	
}
