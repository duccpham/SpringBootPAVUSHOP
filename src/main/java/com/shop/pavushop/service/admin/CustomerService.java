package com.shop.pavushop.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Customer;


@Service
public interface CustomerService {
	public List<Customer> customer();
}
