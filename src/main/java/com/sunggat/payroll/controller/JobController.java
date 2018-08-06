
package com.sunggat.payroll.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunggat.payroll.entity.Job;
import com.sunggat.payroll.service.CrudService;

@Controller
@RequestMapping("/job")
public class JobController {

	private CrudService<Job> jobService;
	
	public JobController(CrudService<Job> jobService) {
		this.jobService = jobService;
	}
	
	@GetMapping("/list")
	public String getJobs (Model theModel) {
		
		List<Job> jobs = jobService.getAll();
		theModel.addAttribute("jobs", jobs);
		
		return "jobs/list";
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Job job = new Job();
		theModel.addAttribute("job", job);
		
		return "jobs/jobForm";
		
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("id") int id, Model theModel) {
		
		Job job = jobService.get(id);
		theModel.addAttribute("job", job);
		
		return "jobs/jobForm";
		
	}
	
	@PostMapping("/saveJob")
	public String saveJob(@ModelAttribute("job") Job job) {
		System.out.println("Job Controller: saveJob method");
		jobService.save(job);
		System.out.println("Job Controller: saveJob method done");
		return "redirect:/job/list";
		
	}
	
}

