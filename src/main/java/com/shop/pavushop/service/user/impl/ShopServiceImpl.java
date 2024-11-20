package com.shop.pavushop.service.user.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Brand;
import com.shop.pavushop.entity.Category;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.BrandRepository;
import com.shop.pavushop.repository.CategoryRepository;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.user.ShopService;


@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Page<Product> findPaginated(Pageable pageable) {
		List<Product> productPage = productRepository.findAll();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		if (productPage.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, productPage.size());
			list = productPage.subList(startItem, toIndex);
		}
		
		Page<Product> productPages = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize),
				productPage.size());

		return productPages;
	}

	@Override
	public List<Category> CategoryList() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Brand> brandList() {
		return brandRepository.findAll();
	}

	@Override
	public List<Product> listProductByCategoryId(Integer id) {
		return productRepository.listProductByCategory(id);
	}

	@Override
	public List<Object[]> coutProductByCategoryName() {
		return productRepository.coutProductByCategory();
	}

	@Override
	public List<Product> productByBrand(Integer id) {
		return productRepository.listProductByBrand(id);
	}

	@Override
	public List<Product> showSearch(String keyword) {
		return productRepository.searchProduct(keyword);
	}

}
