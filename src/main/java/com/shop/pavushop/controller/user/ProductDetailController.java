package com.shop.pavushop.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.service.user.ProductDetailService;

@Controller
public class ProductDetailController extends CommonController {

	@Autowired
	ProductDetailService productDetailService;

	// get productDetail
	@GetMapping(value = "/productDetail")
	public String productDetail(@RequestParam("id") Integer productId, Model model) {
		Product product = productDetailService.productDetail(productId);
		model.addAttribute("product", productDetailService.productDetail(productId));
		productByCategory(model, product.getCategory().getCategoryId());

		return "site/productDetail";
	}

	// Gợi ý sản phẩm cùng loại
	public void productByCategory(Model model, Integer categoryId) {
		List<Product> products = productDetailService.productByCategory(categoryId);
		model.addAttribute("productByCategory", products);

	}

}
