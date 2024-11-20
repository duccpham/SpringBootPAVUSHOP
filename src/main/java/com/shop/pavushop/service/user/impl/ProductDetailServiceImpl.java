package com.shop.pavushop.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.user.ProductDetailService;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product productDetail(Integer productId) {
		return	productRepository.findById(productId).orElse(null);
	}

	@Override
	public List<Product> productByCategory(Integer categoryId) {
		return productRepository.productsByCategory(categoryId);
	}

}
