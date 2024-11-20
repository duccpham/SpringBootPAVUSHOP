package com.shop.pavushop.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Product;

@Service
public interface ProductDetailService {

	public Product productDetail( Integer productId) ;
	public List<Product> productByCategory(Integer categoryId) ;
}
