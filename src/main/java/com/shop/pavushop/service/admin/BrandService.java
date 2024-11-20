package com.shop.pavushop.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Brand;

@Service
public interface BrandService {

	public List<Brand> brands();
	public void addBrand( Brand brand1) ;
	public Brand editBrand(Integer id);
	public void deleteBrand(Integer id);

}
