package vn.edu.likelion.QuanLySach.dto;

import lombok.Data;

@Data
public class BestSellingBookDTO {
	private int id;
	private String name;
	private long salesCount;
	public BestSellingBookDTO(int id, String name, long salesCount) {
		super();
		this.id = id;
		this.name = name;
		this.salesCount = salesCount;
	}
	
	
}
