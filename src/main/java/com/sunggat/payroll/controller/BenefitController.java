package com.sunggat.payroll.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Benefit;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/benefit")
public class BenefitController {

	private CrudService <Benefit> benefitService;
	private CrudService <User> userService;
	
	@Autowired
	public BenefitController(CrudService<Benefit> benefitService, CrudService<User> userService) {
		this.benefitService = benefitService;
		this.userService = userService;
	}

	@GetMapping("/list")
	public String getBenefits (Model theModel) {
		
		List<Benefit> benefits = benefitService.getAll();
		theModel.addAttribute("benefits", benefits);
		
		return "benefits/list";
	}
	
	@GetMapping("/showAddBenefitToUserForm")
	public String showAddBenefitToUserForm(@RequestParam("id") int id, Model theModel) {
		
		Benefit benefit = benefitService.get(id);
		List<User> users = userService.getAll();
		theModel.addAttribute("benefit", benefit);
		theModel.addAttribute("users", users);
		return "benefits/add-user-benefit";
		
	}
	
	@PostMapping("/addBenefitToUser")
	public String addBenefitToUser (@ModelAttribute("benefit") Benefit benefit, 
									@RequestParam("userId") String userId) {
		List<User> users = null;
		User user = userService.get(Integer.valueOf(userId));
		if(benefit.getUsers() == null) {
			users = new ArrayList<>();
		}else {
			users = benefit.getUsers();
		}
		users.add(user);
		benefit.setUsers(users);
		benefitService.save(benefit);
		
		return "benefits/user-benefits";
	}
																			
	
	@PostMapping("/saveBenefit")
	public String saveBenefit(@ModelAttribute("benefit") Benefit benefit) {
		
		benefitService.save(benefit);
		
		return "redirect:/benefit/list";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		Benefit benefit = benefitService.get(id);
		theModel.addAttribute("benefit", benefit);
		
		return "benefits/benefitForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Benefit benefit = new Benefit();
		theModel.addAttribute("benefit", benefit);
		
		return "benefits/benefitForm";
		
	}
	
}
