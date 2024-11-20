package com.shop.pavushop.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Order;
import com.shop.pavushop.service.user.ShoppingCartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Controller
public class ShoppingCartController extends CommonController{

	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping(value = "/cartItem")
	public String shoppingCart(Model model) {
		model.addAttribute("cartItems", shoppingCartService.getCartItems());
		model.addAttribute("totalPrice", shoppingCartService.totalPrice());
		model.addAttribute("totalCartItems", shoppingCartService.getCount());

		return "site/shoppingCart";
	}
	
	// add cartItem
	@GetMapping(value = "/addToCart")
	public String add(@RequestParam("productId") Integer productId, HttpServletRequest request, Model model) {
		shoppingCartService.add(productId);
		
		return "redirect:/cartItem";
	}

	// delete cartItem
	@GetMapping(value = "/remove")
	public String remove(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		shoppingCartService.remove(id);
		return "redirect:/cartItem";
	}

	@GetMapping(value = "/checkOut")
	public String checkout(Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		model.addAttribute("cartItems", shoppingCartService.getCartItems());
		model.addAttribute("totalPrice", shoppingCartService.totalPrice());

		return "site/checkOut";
	}

	// submit checkout
	@PostMapping(value = "/checkOut")
	@Transactional
	public String checkedOut(Model model, Order order, HttpServletRequest request, Principal principal) {
		model.addAttribute("orderId",shoppingCartService.checkedOut(order, request, principal) );

		return "site/checkout_success";

	}
}
