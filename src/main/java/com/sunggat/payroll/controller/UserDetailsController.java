
package com.sunggat.payroll.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Department;
import com.sunggat.payroll.entity.Job;
import com.sunggat.payroll.entity.User;
import com.sunggat.payroll.entity.UserDetails;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/userDetails")
public class UserDetailsController {

	private CrudService <UserDetails> userDetailsService;
	private CrudService <Department> departmentService;
	private CrudService <Job> jobService;
	private CrudService <User> userService;
	
	@Autowired
	public UserDetailsController(CrudService<UserDetails> userDetailsService, CrudService <Department> departmentService, CrudService <Job> jobService, CrudService <User> userService) {
		this.userDetailsService = userDetailsService;
		this.departmentService = departmentService;
		this.jobService = jobService;
		this.userService = userService;
	}

	@GetMapping("/list")
	public String getUserDetails (Model theModel) {
		
		List<UserDetails> userDetails = userDetailsService.getAll();
		theModel.addAttribute("userDetails", userDetails);
		
		return "userDetails/list";
	}
	
	@PostMapping("/saveUserDetails")
	public String saveUserDetails(@ModelAttribute("userDetails") UserDetails userDetails,
								  @RequestParam("departmentId") String departmentId,
								  @RequestParam("jobId") String jobId
								  ) {
		
		Department department = departmentService.get(Integer.valueOf(departmentId));
		Job job = jobService.get(Integer.valueOf(jobId));
		userDetails.setDepartment(department);
		userDetails.setJob(job);
		userDetailsService.save(userDetails);
		
		return "redirect:/user/list";
		
	}
	
	@PostMapping("/addUserDetailsToUser")
	public String addUserDetailsToUser(@ModelAttribute("userDetails") UserDetails userDetails,
								  @RequestParam("departmentId") String departmentId,
								  @RequestParam("jobId") String jobId,
								  @RequestParam("userId") String userId,
								  @RequestParam("experience") String experience) {
		
		Department department = departmentService.get(Integer.valueOf(departmentId));
		Job job = jobService.get(Integer.valueOf(jobId));
		User user = userService.get(Integer.valueOf(userId));
		userDetails.setDepartment(department);
		userDetails.setJob(job);
		userDetails.setExperience(experience);
		user.setUserDetail(userDetails);
		userService.save(user);
		userDetailsService.save(userDetails);
		
		return "redirect:/user/list";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		UserDetails userDetails = userDetailsService.get(id);
		List<Job> jobs = jobService.getAll();
        List<Department> departments = departmentService.getAll();
		theModel.addAttribute("userDetails", userDetails);
		theModel.addAttribute("jobs", jobs);
		theModel.addAttribute("departments", departments);
		
		return "userDetails/userDetailsForm";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(@RequestParam("id") int id, Model theModel) {
		
		UserDetails userDetails = new UserDetails();
		List<Job> jobs = jobService.getAll();
        List<Department> departments = departmentService.getAll();
		theModel.addAttribute("userDetails", userDetails);
		theModel.addAttribute("jobs", jobs);
		theModel.addAttribute("departments", departments);
		theModel.addAttribute("theUser", userService.get(Integer.valueOf(id)));
		
		return "userDetails/add-user-details-to-user";
		
	}
	
	@GetMapping("/showUserDetails") 
	public String showUserDetails (@RequestParam("id") int id, Model theModel) {
		User user = userService.get(id);
		UserDetails userDetails = user.getUserDetail();
		theModel.addAttribute("userDetails", userDetails);
		return "userDetails/user-profile-detailed";
	}
	
	@InitBinder
    public void dateBinding(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }
	
}

