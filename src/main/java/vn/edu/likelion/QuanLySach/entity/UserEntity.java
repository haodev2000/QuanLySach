package vn.edu.likelion.QuanLySach.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "User_QLS")
@Data
public class UserEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String username;
	    private String password;

	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<BookEntity> books;


}
