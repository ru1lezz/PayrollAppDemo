package com.sunggat.payroll.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sunggat.payroll.entity.Benefit;
import com.sunggat.payroll.entity.Job;
import com.sunggat.payroll.entity.Payroll;
import com.sunggat.payroll.entity.Performance;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.service.CrudService;

@Controller
public class MainController {

	private CrudService <User> userService;
	
	
	@Autowired
	public MainController(CrudService<User> userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String showHomePage(Model theModel, Principal principal) {
		if(principal.getName() == null) {
			return "login";
		}else {
			User user = userService.get(Integer.valueOf(principal.getName()));
			theModel.addAttribute("user", user);
			String authority = user.getAuthorities().iterator().next().getAuthority();
			if(authority.equals("ROLE_EMPLOYEE")) {
				return "home";
			} else {
				return "manager-home";
			}
		}
	}
}
