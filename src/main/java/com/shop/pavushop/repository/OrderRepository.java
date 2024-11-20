package com.shop.pavushop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.pavushop.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	/// list order theo tg gan nhat
	
	@Query(value = "SELECT * from orders ORDER BY order_id desc;", nativeQuery = true)
	public List<Order> listOrderByDesc();

}
