package com.shop.pavushop.service.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.pavushop.entity.Brand;
import com.shop.pavushop.entity.Category;
import com.shop.pavushop.entity.Product;



@Service
public interface ShopService {
	
	public Page<Product> findPaginated(Pageable pageable);
	
	public List<Category> CategoryList();
	
	public List<Brand> brandList();
	
	public List<Product> listProductByCategoryId(Integer id);
	
	public List<Object[]> coutProductByCategoryName() ;
	
	public List<Product> productByBrand(Integer id) ;
	
	public List<Product> showSearch( String keyword);

}
