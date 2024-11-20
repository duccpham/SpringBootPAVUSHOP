package com.shop.pavushop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Brand;
import com.shop.pavushop.service.admin.BrandService;

@Controller
public class BrandController extends CommonController {

	@Autowired
	BrandService brandService;


	@GetMapping(value = "/admin/brands")
	public String brands(Model model) {
		Brand brand = new Brand();
		model.addAttribute("brand", brand);
		model.addAttribute("brands", brandService.brands());

		return "admin/brands";
	}

	// add brand
	@PostMapping(value = "/addBrand")
	public String addBrand(@Validated @ModelAttribute("brand") Brand brand1, Model model,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "failure");

			return "admin/brands";
		}
		
		brandService.addBrand(brand1);
		model.addAttribute("message", "successful!");

		return "redirect:/admin/brands";
	}
	
	// get Edit brand
	@GetMapping(value = "/editBrand/{id}")
	public String editCategory(@PathVariable("id") Integer id, Model model) {	
		model.addAttribute("brand", brandService.editBrand(id));
		
		return "admin/editBrand";
	}
	
	// delete brand
	@GetMapping("/deleteBrand/{id}")
	public String delBrand(@PathVariable("id") Integer id, Model model) {
		brandService.deleteBrand(id);
		model.addAttribute("message", "Delete successful!");
		
		return "redirect:/admin/brands";
	}

}
