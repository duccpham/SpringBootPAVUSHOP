package com.shop.pavushop.service.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.pavushop.repository.OrderDetailRepository;
import com.shop.pavushop.service.admin.ReportService;
@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public List<Object[]> reportProduct() throws SQLException {
		return orderDetailRepository.repoProduct();
	}

	@Override
	public List<Object[]> reportCategory() throws SQLException {
		return orderDetailRepository.repoWhereCategory();
	}

	@Override
	public List<Object[]> reportBrands() throws SQLException {
		return orderDetailRepository.repoWhereBrands();
	}

	@Override
	public List<Object[]> reportYear() throws SQLException {
		return orderDetailRepository.repoWhereYear();
	}

	@Override
	public List<Object[]> reportMonth() throws SQLException {
		return orderDetailRepository.repoWhereMonth();
	}

	@Override
	public List<Object[]> reportQuarter() throws SQLException {
		return orderDetailRepository.repoWhereQUARTER();
	}

	@Override
	public List<Object[]> reportOrderCustomer() throws SQLException {
		return orderDetailRepository.reportCustommer();
	}

}
