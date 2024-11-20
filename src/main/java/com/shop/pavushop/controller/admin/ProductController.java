package com.shop.pavushop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.service.admin.ProductService;
@Controller
public class ProductController extends CommonController {

	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/admin/products")
	public String products(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("products", productService.products());
		return "admin/products";
	}

	// add product
	@PostMapping(value = "/addProduct")
	public String addProduct(@ModelAttribute("product") Product product, Model model,
			@RequestParam("file") MultipartFile file) {

		Product p = productService.addProduct(product, file);
		if (null != p) {
			model.addAttribute("message", "Update success");
		} else {
			model.addAttribute("error", "Update failure");
		}
		return "redirect:/admin/products";
	}

	
	@ModelAttribute("categoryList")
	public void showCategory(Model model) {
		model.addAttribute("categoryList", productService.CategoryList());
	}

	
	@ModelAttribute("brandList")
	public void brandList(Model model) {
		model.addAttribute("brandList", productService.brandList());
	}
	
	// get Edit product
	@GetMapping(value = "/editProduct/{id}")
	public String editCategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.editProduct(id));
		
		return "admin/editProduct";
	}

	// delete product
	@GetMapping("/deleteProduct/{id}")
	public String delProduct(@PathVariable("id") Integer id, Model model) {
		productService.deleteProduct(id);
		model.addAttribute("message", "Delete successful!");
		
		return "redirect:/admin/products";
	}
}
