package com.shop.pavushop.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.service.admin.ReportService;

@Controller
public class ReportController extends CommonController {
	

	@Autowired
	ReportService reportService;
	
	// Thống kê theo sản phẩm được bán ra
		@GetMapping(value = "/admin/reports")
		public String reportProduct(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportProduct());
			
			return "admin/statistical";
		}

		// Thống kê theo thể loại được bán ra
		@GetMapping("/admin/reportCategory")
		public String reportcategory(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportCategory());
			
			return "admin/statistical";
		}

		// Thống kê theo sản phẩm từ nhà cung cấp được bán ra
		@GetMapping("/admin/reportBrands")
		public String reportbrands(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportBrands());
			
			return "admin/statistical";
		}

		// Thống kê sản phẩm bán ra theo năm
		@GetMapping("/admin/reportYear")
		public String reportyear(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportYear());
			
			return "admin/statistical";
		}

		// Thống kê sản phẩm bán ra theo tháng
		@GetMapping("/admin/reportMonth")
		public String reportmonth(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportMonth());
		
			return "admin/statistical";
		}

		// Thống kê sản phẩm bán ra theo quý
		@GetMapping("/admin/reportQuarter")
		public String reportquarter(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportQuarter());
			
			return "admin/statistical";
		}

		// Thống kê theo người dùng
		@GetMapping("/admin/reportOrderCustomer")
		public String reportordercustomer(Model model) throws SQLException {
			model.addAttribute("listReportCommon", reportService.reportOrderCustomer());

			return "admin/statistical";
		}


}
