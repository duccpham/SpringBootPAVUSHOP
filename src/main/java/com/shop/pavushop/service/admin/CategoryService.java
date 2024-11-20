package com.shop.pavushop.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Category;

@Service
public interface CategoryService {

	public List<Category> categories() ;
	
	public void addCategory(Category category) ;

	public Category editCategory (Integer id) ;
	
	public void deleteCategory(Integer id) ;
}
