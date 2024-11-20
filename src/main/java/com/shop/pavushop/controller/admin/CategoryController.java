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
import com.shop.pavushop.entity.Category;
import com.shop.pavushop.service.admin.CategoryService;

@Controller
public class CategoryController extends CommonController {
	@Autowired
	CategoryService categoryService;
	

		@GetMapping(value = "/admin/categories")
		public String categories(Model model) {
			Category category = new Category();
			model.addAttribute("category", category);
			model.addAttribute("categories", categoryService.categories());
			return "admin/categories";
		}

		// add category
		@PostMapping(value = "/addCategory")
		public String addCategory(@Validated @ModelAttribute("category") Category category, Model model,
				BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("error", "failure");
				return "admin/categories";
			}
			categoryService.addCategory(category);
			model.addAttribute("message", "successful!");
			return "redirect:/admin/categories";
		}
		
		// get Edit category
		@GetMapping(value = "/editCategory/{id}")
		public String editCategory(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("category", categoryService.editCategory(id));
			return "admin/editCategory";
		}

		// delete category
		@GetMapping("/delete/{id}")
		public String delCategory(@PathVariable("id") Integer id, Model model) {
			categoryService.deleteCategory(id);
			model.addAttribute("message", "Delete successful!");
			return "redirect:/admin/categories";
		}
}
