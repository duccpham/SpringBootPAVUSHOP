package com.shop.pavushop.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.OrderDetailRepository;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.user.IndexService;
@Service
public class IndexServiceImpl implements IndexService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	

	@Override
	public List<Product> listProduct8() {
		return productRepository.listProduct8();
	}

	@Override
	public List<Object[]> topOrder() {
		return orderDetailRepository.topOrder();
	}

}
