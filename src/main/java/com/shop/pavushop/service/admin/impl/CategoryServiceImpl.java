package com.shop.pavushop.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Category;
import com.shop.pavushop.repository.CategoryRepository;
import com.shop.pavushop.service.admin.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public List<Category> categories() {
		return categoryRepository.findAll();
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category editCategory(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}

}
