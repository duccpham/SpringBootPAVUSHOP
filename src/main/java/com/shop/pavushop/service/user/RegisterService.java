package com.shop.pavushop.service.user;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.shop.pavushop.entity.Customer;



@Service
public interface RegisterService {
	
	public Customer addUser(Customer customer, BindingResult result) ;
	public boolean checkEmail(String email) ;
	public boolean checkIdlogin(String customerId) ;

}
