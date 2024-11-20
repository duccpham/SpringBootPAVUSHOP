package com.shop.pavushop.service.admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {

	public List<Object[]> reportProduct() throws SQLException;
	
	public List<Object[]> reportCategory() throws SQLException ;
	
	public List<Object[]> reportBrands() throws SQLException ;
	
	public List<Object[]> reportYear() throws SQLException ;
	
	public List<Object[]> reportMonth() throws SQLException ;
	
	public List<Object[]> reportQuarter() throws SQLException ;
	
	public List<Object[]> reportOrderCustomer() throws SQLException;

}
