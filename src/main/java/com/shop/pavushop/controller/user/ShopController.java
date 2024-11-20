package com.shop.pavushop.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.user.ShopService;


@Controller
public class ShopController extends CommonController {
	@Autowired
	ShopService shopService;
	@Autowired 
	ProductRepository productRepository;
	
	@GetMapping(value = "/products")
	public String shop(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		model.addAttribute("brandList", shopService.brandList());
		model.addAttribute("categoryList", shopService.CategoryList());
		int currentPagedf = page.orElse(1);
		int pageSizedf = size.orElse(9);
		Page<Product> productPage = shopService.findPaginated(PageRequest.of(currentPagedf - 1, pageSizedf));
		int totalPages = productPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("productList", productPage);
		coutProductByCategoryName(model);

		return "site/shop";
	}

	
	// hiển thị sản phẩm theo category
	@GetMapping(value = "/productByCategory")
	public String listProductById(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("productList", shopService.listProductByCategoryId(id));
		model.addAttribute("brandList", shopService.brandList());
		coutProductByCategoryName(model);
		return "site/shop";
	}
	
	// hiển thị sản phẩm theo brand
	@GetMapping(value = "/productByBrand")
	public String productBy(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("productList", shopService.productByBrand(id));
		model.addAttribute("brandList", shopService.brandList());
		coutProductByCategoryName(model);
		
		return "site/shop";
	}
	
	// search product
	@GetMapping(value = "/searchProduct")
	public String showSearch(Model model, @RequestParam("keyword") String keyword) {
		model.addAttribute("productList", shopService.showSearch(keyword));
		model.addAttribute("brandList", shopService.brandList());
		coutProductByCategoryName(model);
		
		return "site/shop";
	}
	// Hiển thị mỗi thể loại có bao nhiêu sản phẩm
	public void coutProductByCategoryName(Model model) {
		model.addAttribute("coutnProductByCategory", shopService.coutProductByCategoryName());
		}

}
