package com.shop.pavushop.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Brand;
import com.shop.pavushop.repository.BrandRepository;
import com.shop.pavushop.service.admin.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandRepository brandRepository;

	@Override
	public List<Brand> brands() {
		return	brandRepository.findAll();
	}

	@Override
	public void addBrand(Brand brand1) {
		brandRepository.save(brand1);
		
	}

	@Override
	public Brand editBrand(Integer id) {
		return brandRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteBrand(Integer id) {
		brandRepository.deleteById(id);
	}

}
