package com.shop.pavushop.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Order;
import com.shop.pavushop.repository.OrderDetailRepository;
import com.shop.pavushop.repository.OrderRepository;
import com.shop.pavushop.service.admin.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> orders() {
		return orderRepository.listOrderByDesc();	
		}

	@Override
	public Order showEditOrder(int order) {
		return orderRepository.findById(order).orElse(null);
	}

	@Override
	public Order editorder(Order order) {
		return orderRepository.save(order);
	}

	

}
