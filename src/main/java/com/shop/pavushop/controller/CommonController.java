package com.shop.pavushop.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shop.pavushop.entity.CartItem;
import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.service.user.ShoppingCartService;

@Controller
public class CommonController {
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@ModelAttribute(value = "customer")
	public Customer initCustomer(Principal principal) {
		Customer customer = new Customer();
		if (principal != null) {
		customer = (Customer) ((Authentication) principal).getPrincipal();		}
		return customer;
		} 
	
	// active - font-end cart->header
		@ModelAttribute("cartItems")
		public Collection<CartItem> cartItems2(Model model) {
			Collection<CartItem> cartItems = shoppingCartService.getCartItems();
			model.addAttribute("cartItems", cartItems);
			double totalPrice = 0;
			for (CartItem cartItem : cartItems) {
				double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
				totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
			}

			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("totalCartItems", shoppingCartService.getCount());

			return cartItems;
		}

}
