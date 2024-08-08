package vn.edu.likelion.QuanLySach.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	 private int id;
	 private String username;
	 private String password;
	 private List<BookDTO> books;
}
