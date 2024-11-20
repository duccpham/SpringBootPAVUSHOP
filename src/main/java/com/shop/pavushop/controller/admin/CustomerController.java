package com.shop.pavushop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.service.admin.CustomerService;

@Controller
public class CustomerController extends CommonController{
	@Autowired
	CustomerService customerService;

	@GetMapping(value = "/admin/customers")
	public String customer(Model model) {	
		model.addAttribute("customers", customerService.customer());
		return "/admin/customers";
	}

}
