package com.shop.pavushop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.pavushop.controller.CommonController;
import com.shop.pavushop.service.user.IndexService;


@Controller
public class IndexController extends CommonController  {

	@Autowired
	IndexService indexService;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("productList", indexService.listProduct8() );
        model.addAttribute("topOrderList", indexService.topOrder());

		return "site/index";
	}
}
