package com.sunggat.payroll.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showDemoPage(Model theModel) {
		return "redirect:/job/list";
	}
}
