
package com.sunggat.payroll.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Payroll;
import com.sunggat.payroll.entity.Performance;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.entity.UserDetails;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

private CrudService <Payroll> payrollService;
private CrudService <User> userService;
	
	@Autowired
	public PayrollController(CrudService<Payroll> payrollService, CrudService <User> userService) {
		this.payrollService = payrollService;
		this.userService = userService;
	}

	@GetMapping("/list")
	public String getPayrolls (Model theModel) {
		
		List<Payroll> payrolls = payrollService.getAll();
		theModel.addAttribute("payrolls", payrolls);
		
		return "payroll/list";
	}
	
	@PostMapping("/savePayroll")
	public String savePayroll(@ModelAttribute("payroll") Payroll payroll, @RequestParam("userId") String userId) {
		
		payroll.setUser(userService.get(Integer.valueOf(userId)));
		payrollService.save(payroll);
		
		return "redirect:/payroll/list";
		
	}
	
	@GetMapping("/calculateSalary")
	public String calculateSalary(@RequestParam("id") int id) {
		Payroll payroll = payrollService.get(id);
		User user = payroll.getUser();
		UserDetails userDetails = user.getUserDetail();
		String experience = userDetails.getExperience();
		List<Performance> userPerformances = user.getPerformances();
		Date joinDate = userDetails.getJoinDate();
		double loyalty = checkJoinDate(joinDate);
		int mark = getFullMark(userPerformances);
		double multiplierMark = checkMark(mark);
		double multiplierExp = checkExperience(experience);
		double salary = payroll.getAnnualSalary().doubleValue();
		
		double finalSalary = (salary * multiplierExp + (salary * multiplierMark - salary)) * loyalty;
		
		payroll.setFinalSalary(BigDecimal.valueOf(finalSalary));
		payrollService.save(payroll);
		return "redirect:/payroll/list";
	}
	
	public double checkJoinDate(Date joinDate) {
		@SuppressWarnings("deprecation")
		int year = joinDate.getYear();
		if(year < 1) {
			return 1;
		}
		return 1.05;
	}
	
	public double checkMark(int mark) {
		if(mark >= 90) {
			return 1.2; 
		}else if(mark >= 80) {
			return 1.15;
		}else if(mark >= 70) {
			return 1.1;
		}else if(mark >= 60) {
			return 1.05;
		}else {
			return 1;
		}
	}
	
	public int getFullMark(List<Performance> userPerformances) {
		int mark = 0;
		if(userPerformances == null) {
			return 0;
		} else {
			for(int i = 1; i <= userPerformances.size(); i++) {
				Performance p = userPerformances.get(i-1);
				mark += p.getMark();
				mark = mark/i;
			}
		}
		return mark;
	}
	
	public double checkExperience(String experience) {
		if(experience.equals("Master")) {
			return 1.3;
		} else if (experience.equals("Advanced")) {
			return 1.2;
		} else if (experience.equals("Intermediate")) {
			return 1.1;
		}
		return 1;
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		Payroll payroll = payrollService.get(id);
		theModel.addAttribute("payroll", payroll);
		theModel.addAttribute("users", userService.getAll());
		
		return "payroll/payrollForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Payroll payroll = new Payroll();
		theModel.addAttribute("payroll", payroll);
		theModel.addAttribute("users", userService.getAll());
		
		return "payroll/payrollForm";
		
	}
	
}
