package com.shop.pavushop.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController{

	@RequestMapping("/error")
	public String handleError(Model model) {	
		return "site/notFound";
	}

	public String getErrorPath() {
		return null;
	}
}
