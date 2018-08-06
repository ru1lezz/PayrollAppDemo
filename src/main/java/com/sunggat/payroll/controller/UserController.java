package com.sunggat.payroll.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Benefit;
import com.sunggat.payroll.entity.Performance;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.entity.UserDetails;
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
	
	@GetMapping("/listUserBenefits")
	public String getUserBenefits (@RequestParam("id") int id, Model theModel) {
		
		User user = userService.get(id);
		List<Benefit> benefits = user.getBenefits();
		if(benefits == null) {
			benefits = new ArrayList<>();
		}
		theModel.addAttribute("user", user);
		theModel.addAttribute("benefits", benefits);
		return "benefits/user-benefits";
		
	}
	
	@GetMapping("/listUserPerformances")
	public String getUserPerformances (@RequestParam("id") int id, Model theModel) {
		
		User user = userService.get(id);
		List<Performance> performances = user.getPerformances();
		if(performances == null) {
			performances = new ArrayList<>();
		}
		theModel.addAttribute("user", user);
		theModel.addAttribute("performances", performances);
		return "performance/user-performances";
		
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		String password = user.getPassword();
		user.setEnabled(true);
		if(!password.startsWith("$2a$")) {
			String encodedPassword = new BCryptPasswordEncoder().encode(password);
			user.setPassword(encodedPassword);
			
		}
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
	
	@GetMapping("/showUserProfile")
	public String showUserProfile(Model theModel, Principal principal) {
		
		User user = userService.get(Integer.valueOf(principal.getName()));
		theModel.addAttribute("user", user);
		
		return "users/user-profile";
		
	}
	
	@InitBinder
    public void dateBinding(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
}
