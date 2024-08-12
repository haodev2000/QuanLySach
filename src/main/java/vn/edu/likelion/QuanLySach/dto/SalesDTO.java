package vn.edu.likelion.QuanLySach.dto;


import java.util.Date;

import lombok.Data;

@Data
public class SalesDTO {
	
	    private int id;

	    private BookDTO book;

	    private Date dateSale;

}
