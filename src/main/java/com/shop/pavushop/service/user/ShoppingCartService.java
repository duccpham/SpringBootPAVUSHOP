package com.shop.pavushop.service.user;
import java.security.Principal;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.CartItem;
import com.shop.pavushop.entity.Order;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface ShoppingCartService {
	int getCount();

	void clear();
	
	Collection<CartItem> getCartItems();

	void remove(Integer id);
	
	Double totalPrice();

	void add(Integer productId);
	

	int checkedOut(Order order, HttpServletRequest request, Principal principal);

}
