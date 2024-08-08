package vn.edu.likelion.QuanLySach.service;

import java.util.List;

import vn.edu.likelion.QuanLySach.dto.SalesDTO;

public interface SalesService {

		List<SalesDTO> getAllSales();
	    SalesDTO getSaleById(int id);
	    SalesDTO createSale(int bookId);
	    void deleteSale(int id);
}
