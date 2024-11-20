package com.shop.pavushop.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Order;

@Service
public interface OrderService {
	
    public List<Order> orders() ;
	public Order showEditOrder(int orderDetailId) ;
	public Order editorder(Order order) ;

}
