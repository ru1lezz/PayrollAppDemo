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

import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/user")
public class UserController {

	private CrudService <User> userService;
	
	@Autowired
	public UserController(CrudService<User> userService) {
		this.userService = userService;
	}

	@GetMapping("/list")
	public String getUsers (Model theModel) {
		
		List<User> users = userService.getAll();
		theModel.addAttribute("users", users);
		
		return "users/list";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		
		userService.save(user);
		
		return "redirect:/user/list";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		User user = userService.get(id);
		theModel.addAttribute("user", user);
		
		return "users/userForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		User user = new User();
		theModel.addAttribute("user", user);
		
		return "users/userForm";
		
	}
	
	
	
}
