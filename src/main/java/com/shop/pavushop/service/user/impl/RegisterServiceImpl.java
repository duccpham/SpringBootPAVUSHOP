package com.shop.pavushop.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.entity.Role;
import com.shop.pavushop.repository.CustomerRepository;
import com.shop.pavushop.service.user.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	PasswordEncoder PasswordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public Customer addUser(Customer customer, BindingResult result) {
		customer.setEnabled(true);
		customer.setRoleId("0");
		customer.setPassword(PasswordEncoder.encode(customer.getPassword()));
		Date date = new Date();
		customer.setCreateDate(date);
		Customer c = customerRepository.save(customer);
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		role.setCustomer(c);
		return c;	
	}
	// check email
	@Override
	public boolean checkEmail(String email) {
		List<Customer> list = customerRepository.findAll();
		for (Customer c : list) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkIdlogin(String customerId) {
		List<Customer> listC = customerRepository.findAll();
		for (Customer c : listC) {
			if (c.getCustomerId().equalsIgnoreCase(customerId)) {
				return false;
			}
		}
		return true;
	}

}
