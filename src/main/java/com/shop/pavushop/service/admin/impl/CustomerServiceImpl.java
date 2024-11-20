package com.shop.pavushop.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.repository.CustomerRepository;
import com.shop.pavushop.service.admin.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	

	@Override
	public List<Customer> customer() {
		return customerRepository.findAll();
	}

}
