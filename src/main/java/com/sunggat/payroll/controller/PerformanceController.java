
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

import com.sunggat.payroll.entity.Performance;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    private CrudService <Performance> performanceService;
	private CrudService <User> userService;
    
	@Autowired
	public PerformanceController(CrudService<Performance> performanceService, CrudService <User> userService) {
		this.performanceService = performanceService;
		this.userService = userService;
	}

	@GetMapping("/list")
	public String getPerformances (Model theModel) {
		
		List<Performance> performances = performanceService.getAll();
		theModel.addAttribute("performances", performances);
		
		return "performance/list";
	}
	
	@PostMapping("/savePerformance")
	public String savePerformance(@ModelAttribute("performance") Performance performance) {
		
		performanceService.save(performance);
		
		return "redirect:/performance/list";
		
	}
	
	@PostMapping("/addPerformanceToUser")
	public String addPerformanceToUser(@RequestParam("performanceId") String performanceId,
									   @RequestParam("userId") String userId) {
		
		Performance performance = performanceService.get(Integer.valueOf(performanceId));
		List<User> users = performance.getUsers();
		if(users == null) {
			users = new ArrayList<>();
		}
		User user = userService.get(Integer.valueOf(userId));
		users.add(user);
		performance.setUsers(users);
		performanceService.save(performance);
		
		return "redirect:/performance/list";
		
	}
	
	@GetMapping("/showAddPerformanceToUser")
	public String showAddToUserPerformance (Model theModel) {
		
		List<Performance> performances = performanceService.getAll();
		List<User> users = userService.getAll();
		if(performances == null) {
			performances = new ArrayList<>();
		}
		if(users == null) {
			users = new ArrayList<>();
		}
		theModel.addAttribute("performances", performances);
		theModel.addAttribute("users", users);
		
		return "performance/add-user-performance";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		Performance performance = performanceService.get(id);
		theModel.addAttribute("performance", performance);
		
		return "performance/performanceForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Performance performance = new Performance();
		theModel.addAttribute("performance", performance);
		
		return "performance/performanceForm";
	}
}

