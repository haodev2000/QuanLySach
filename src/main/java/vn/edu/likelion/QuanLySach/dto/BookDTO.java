package vn.edu.likelion.QuanLySach.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Datapublic class BookDTO {

    private int id;

    private String name;

    private int quantity;
    
    private float price;

    private Date dateAdded;

//    private List<SalesDTO> sales;	

	public BookDTO() {
		super();
	}

    // Getters and Setters
    
    
}
