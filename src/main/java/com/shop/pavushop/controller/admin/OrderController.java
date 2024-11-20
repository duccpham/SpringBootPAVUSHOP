package com.shop.pavushop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.entity.Order;
import com.shop.pavushop.service.admin.OrderService;

@Controller
public class OrderController extends CommonController {
	@Autowired
	OrderService orderService;
	
	// list order
	@GetMapping(value = "/admin/orders")
	public String orders(Model model) {
		model.addAttribute("order", orderService.orders());
		return "admin/orders";
	}
	
	// get edit
	@GetMapping("/editorder/{orderId}")
	public String showEditOrder(@PathVariable("orderId") int orderId, Model model) {
		model.addAttribute("order", orderService.showEditOrder(orderId));
		return "admin/editOrder";
	}

	// edit order
	@PostMapping("/editorder")
	public String editordertr(@ModelAttribute("order") Order order, Model model,
			RedirectAttributes rs) {
		Order order2 = orderService.editorder(order);
		if (null != order2) {
			model.addAttribute("message", "Đã xác nhận !");
		} else {
			model.addAttribute("message", "Cập nhật thất bại !");
			model.addAttribute("orderDetail", order);
		}
		return "redirect:/admin/orders";
	}
	

}
