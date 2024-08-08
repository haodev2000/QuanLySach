package vn.edu.likelion.QuanLySach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.likelion.QuanLySach.entity.BookEntity;
import vn.edu.likelion.QuanLySach.entity.SalesEntity;

@Repository
public interface SaleRepository extends JpaRepository<SalesEntity, Integer> {

	  int countByBook(BookEntity book);
	  
	  
}
