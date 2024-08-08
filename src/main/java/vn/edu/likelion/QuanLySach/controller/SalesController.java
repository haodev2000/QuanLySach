package vn.edu.likelion.QuanLySach.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.likelion.QuanLySach.dto.SalesDTO;
import vn.edu.likelion.QuanLySach.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

	 	@Autowired
	    private SalesService salesService;

	    // Get all sales
	    @GetMapping
	    public ResponseEntity<List<SalesDTO>> getAllSales() {
	        List<SalesDTO> sales = salesService.getAllSales();
	        return new ResponseEntity<>(sales, HttpStatus.OK);
	    }

	    // Get sale by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<SalesDTO> getSaleById(@PathVariable int id) {
	        SalesDTO sale = salesService.getSaleById(id);
	        if (sale != null) {
	            return new ResponseEntity<>(sale, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	

	    // Delete sale
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
	        salesService.deleteSale(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
