package com.shop.pavushop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.pavushop.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	// Hiển thị danh sách product mới nhất ở trang chủ LIMIT = 8
			@Query(value = "SELECT * FROM products ORDER BY entered_date DESC limit 8", nativeQuery = true)
			public List<Product> listProduct8();
			
			
			// Hiển thị mỗi thể loại có bao nhiêu sản phẩm
			@Query(value = "SELECT c.category_id,c.category_name,COUNT(*) AS"
					+ " SoLuong FROM products b JOIN categories c ON b.category_id = c.category_id "
					+ "GROUP BY c.category_name;" , nativeQuery = true)	
			public List<Object[]> coutProductByCategory();

			// sản phẩm theo danh mục
			@Query(value = "SELECT * FROM products where category_id = ?", nativeQuery = true)
			public List<Product> listProductByCategory(Integer id);

			// Sản phẩm theo danh nhãn hiệu
			@Query(value = "SELECT * FROM products where brand_id = ?", nativeQuery = true)
			public List<Product> listProductByBrand(Integer id);
			
			// Gợi ý sản phẩm cùng thể loại
			@Query(value = "SELECT \r\n"
					+ "*FROM products AS p\r\n"
					+ "WHERE p.category_id = ?" , nativeQuery = true)
			public List<Product> productsByCategory(Integer categoryId);
			
			// Search Product
			@Query(value = "SELECT * FROM products WHERE name LIKE %?1%", nativeQuery = true)
			public List<Product> searchProduct(String name);
	
}
