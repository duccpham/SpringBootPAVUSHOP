package com.shop.pavushop.service.user.impl;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.CartItem;
import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.entity.Order;
import com.shop.pavushop.entity.OrderDetail;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.CustomerRepository;
import com.shop.pavushop.repository.OrderDetailRepository;
import com.shop.pavushop.repository.OrderRepository;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.user.ShoppingCartService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();

	@Override
	public int getCount() {

		Collection<CartItem> cartItems = getCartItems();
		int quantitys =0;
		for (CartItem cartItem : cartItems) {
			int quantity  = cartItem.getQuantity();
		        quantitys += quantity;
		}
		return quantitys;
		
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	

	@Override
	public void remove(Integer id) {
			map.remove(id);
	}

	@Override
	public Double totalPrice() {
		Collection<CartItem> cartItems = getCartItems();
		double totalPrice = 0;
		for (CartItem cartItem : cartItems) {
			double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
			totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
		}
		return totalPrice;
	}

	@Override
	public void add(Integer productId) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product != null) {
			CartItem item = new CartItem();
			BeanUtils.copyProperties(product, item);
			item.setQuantity(1);
			item.setProduct(product);
			item.setProductId(productId);;
			CartItem existedItem = map.get(item.getProductId());
			if (existedItem != null) {
				existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
				existedItem.setTotalPrice(item.getTotalPrice() + existedItem.getUnitPrice() * existedItem.getQuantity());
			} else {
				map.put(item.getProductId(), item);
			}
		}
		
	}

	@Override
	public int checkedOut(Order order, HttpServletRequest request, Principal principal) {
		Collection<CartItem> cartItems = getCartItems();
		Customer c = customerRepository.findByEmail(principal.getName()).orElse(null);
		double totalPrice = 0;
		for (CartItem cartItem : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(cartItem.getQuantity());
			orderDetail.setOrder(order);
			orderDetail.setProduct(cartItem.getProduct());

			double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
			totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);

			double unitPrice = cartItem.getProduct().getPrice();

			orderDetail.setTotalPrice(price - (price * cartItem.getProduct().getDiscount() / 100));
			orderDetail.setPrice(unitPrice);
			order.setStatus("Đang Chờ Xử Lý");
			orderDetailRepository.save(orderDetail);

		}

		order.setTotalPrice(totalPrice);
		Date date = new Date();
		order.setOrderDate(date);
		order.setCustomer(c);
		orderRepository.save(order);
		getCartItems().clear();
		int orderId =  order.getOrderId();
		return orderId;
	}

	
}
