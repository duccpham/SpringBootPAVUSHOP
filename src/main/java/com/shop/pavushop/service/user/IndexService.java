package com.shop.pavushop.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Product;

@Service
public interface IndexService {
	public List<Product> listProduct8();
	public List<Object[]> topOrder();

}
