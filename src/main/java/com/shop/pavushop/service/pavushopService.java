package com.shop.pavushop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.pavushop.entity.Customer;
import com.shop.pavushop.repository.CustomerRepository;


public class pavushopService implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
		Optional<Customer> user = customerRepository.findCustomersLogin(customerId);
		final Customer customerLogin = new Customer();
		customerLogin.setEnabled(user.get().getEnabled());
		customerLogin.setCustomerId(user.get().getCustomerId());
		customerLogin.setEmail(user.get().getEmail());
		customerLogin.setPassword(user.get().getPassword());
		customerLogin.setFullname(user.get().getFullname());
		customerLogin.setRoleId(user.get().getRoleId());
		return customerLogin;
	}
}
