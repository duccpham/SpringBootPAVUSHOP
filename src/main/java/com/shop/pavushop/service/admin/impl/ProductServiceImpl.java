package com.shop.pavushop.service.admin.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.pavushop.entity.Brand;
import com.shop.pavushop.entity.Category;
import com.shop.pavushop.entity.Product;
import com.shop.pavushop.repository.BrandRepository;
import com.shop.pavushop.repository.CategoryRepository;
import com.shop.pavushop.repository.ProductRepository;
import com.shop.pavushop.service.admin.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Value("${upload.path}")
	private String pathUploadImage;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	BrandRepository brandRepository;
	@Override
	public List<Product> products() {
		return  productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product, MultipartFile file) {

		try {

			File convFile = new File(pathUploadImage + "/" + file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			
		}
		product.setImage(file.getOriginalFilename());
		return productRepository.save(product);
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
	public Product editProduct(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

}
