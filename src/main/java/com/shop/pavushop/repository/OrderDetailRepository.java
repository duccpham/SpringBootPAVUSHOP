package com.shop.pavushop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.pavushop.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	

	// Top 10 order nhiều nhất
    @Query(value = "SELECT  p.name ,  p.image, o.product_id, SUM(o.quantity) as quantity , p.price, p.discount\r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN products p ON o.product_id = p.product_id\r\n"
    		+ "group by product_id\r\n"
    		+ "order by quantity desc\r\n"
    		+ "limit 10;", nativeQuery = true)
    public List<Object[]> topOrder();
    
 // thống kê theo sản phẩm được bán ra
    @Query(value = "SELECT p.name ,  \r\n"
    		+ "SUM(o.quantity) as quantity ,\r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg,\r\n"
    		+ "Min(o.total_price) as min, \r\n"
    		+ "max(o.total_price) as max\r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN products p ON o.product_id = p.product_id\r\n"
    		+ "GROUP BY p.name;", nativeQuery = true)

    public List<Object[]> repoProduct();
    
    // Thống kê theo thể loại được bán ra
    @Query(value = "SELECT c.category_name , \r\n"
    		+ "SUM(o.quantity) as quantity ,\r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg, \r\n"
    		+ "Min(o.total_price) as min,\r\n"
    		+ "max(o.total_price) as max \r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN products p ON o.product_id = p.product_id\r\n"
    		+ "INNER JOIN categories c ON p.category_id = c.category_id\r\n"
    		+ "GROUP BY c.category_name;", nativeQuery = true)

    public List<Object[]> repoWhereCategory();
    
    // Thống kê các sp từ nhà cung cấp được bán ra
    @Query(value = "SELECT s.brand_name , \r\n"
    		+ "SUM(o.quantity) as quantity ,\r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg  ,\r\n"
    		+ "Min(o.total_price) as min  ,\r\n"
    		+ "max(o.total_price) as max \r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN products p ON o.product_id = p.product_id\r\n"
    		+ "INNER JOIN brands s ON p.brand_id = s.brand_id\r\n"
    		+ "GROUP BY s.brand_name;", nativeQuery = true)

    public List<Object[]> repoWhereBrands();
    
    // Thống kê sản phẩm theo năm // theo các năm
    @Query(value = "Select YEAR(od.order_date) ,\r\n"
    		+ "SUM(o.quantity) as quantity ,\r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg  ,\r\n"
    		+ "Min(o.total_price) as min  ,\r\n"
    		+ "max(o.total_price) as max \r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN orders od ON o.order_id =od.order_id\r\n"
    		+ "GROUP BY YEAR(od.order_date);", nativeQuery = true)
    public List<Object[]> repoWhereYear();
    
    // Thống kê sản phẩm theo tháng // theo các Tháng
    @Query(value = "Select month(od.order_date) ,\r\n"
    		+ "SUM(o.quantity) as quantity ,    \r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg  ,\r\n"
    		+ "Min(o.total_price) as min  ,\r\n"
    		+ "max(o.total_price) as max\r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN orders od ON o.order_id =od.order_id\r\n"
    		+ "GROUP BY month(od.order_date);", nativeQuery = true)

    public List<Object[]> repoWhereMonth();
    
    // Thống kê sản phẩm theo quý // theo các quý
    @Query(value = "Select QUARTER(od.order_date),\r\n"
    		+ "SUM(o.quantity) as quantity , \r\n"
    		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
    		+ "AVG(o.total_price) as avg, \r\n"
    		+ "Min(o.total_price) as min,\r\n"
    		+ "max(o.total_price) as max\r\n"
    		+ "FROM order_details o\r\n"
    		+ "INNER JOIN orders od ON o.order_id =od.order_id\r\n"
    		+ "GROUP By QUARTER(od.order_date);", nativeQuery = true)

    public List<Object[]> repoWhereQUARTER();
    
    // Thống kê sản phẩm theo người đặt hàng
   @Query(value = "SELECT c.customer_id,\r\n"
   		+ "SUM(o.quantity) as quantity,  \r\n"
   		+ "SUM(o.quantity * o.total_price) as sum,\r\n"
   		+ "AVG(o.total_price) as avg,\r\n"
   		+ "Min(o.total_price) as min, \r\n"
   		+ "max(o.total_price) as max \r\n"
   		+ "FROM order_details o\r\n"
   		+ "INNER JOIN orders p ON o.order_id = p.order_id\r\n"
   		+ "INNER JOIN customers c ON p.customer_id = c.customer_id\r\n"
   		+ "GROUP BY c.customer_id;", nativeQuery = true)
   public List<Object[]> reportCustommer();

}
