package com.shop.pavushop.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.service.user.RegisterService;

import jakarta.validation.Valid;

@Controller
public class RegisterController extends CommonController {

	@Autowired
	RegisterService registerService;
	
	@GetMapping(value = "/registered")
	public String register() {

		return "site/register";
	}

	// register
	@PostMapping(value = "/registered")
	public String addCourse(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, ModelMap model,
			Principal principal) {
		// check error	
		if (result.hasErrors()) {
			return "site/register";
		}
		// check email by database
		if (!registerService.checkEmail(customer.getEmail())) {
			model.addAttribute("error", "Đăng ký thất bại, Email này đã được sử dụng!");
			return "site/register";
		}
		
		if (!registerService.checkIdlogin(customer.getCustomerId())) {
			model.addAttribute("error", "Đăng kí thất bại, ID Login này đã được sử dụng!");	
			return "site/register";
		}
		
		if (null != registerService.addUser(customer, result)) {
			model.addAttribute("message", "Đăng ký thành công, vui lòng đăng nhập!");
			model.addAttribute("customer", customer);
		} else {
			model.addAttribute("error", "failure");
			model.addAttribute("customer", customer);
		}
		return "site/register";
	}
	
}
