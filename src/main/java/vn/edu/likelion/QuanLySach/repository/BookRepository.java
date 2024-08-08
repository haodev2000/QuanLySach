package vn.edu.likelion.QuanLySach.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.likelion.QuanLySach.dto.BestSellingBookDTO;
import vn.edu.likelion.QuanLySach.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	
//	 List<BookEntity> findAllByOrderByPriceAsc();
	 
	 @Query(value = "SELECT * FROM Book ORDER BY price ASC", nativeQuery = true)
	    List<BookEntity> findAllByOrderByPriceAsc();
	 
	 @Query(value = "SELECT *"
	 		+ "FROM ("
	 		+ "    SELECT b.id, b.name, COALESCE(COUNT(s.id), 0) AS sales_count"
	 		+ "    FROM Book b "
	 		+ "    LEFT JOIN Sales s "
	 		+ "    ON b.id = s.book_id "
	 		+ "    GROUP BY b.id, b.name "
	 		+ "    ORDER BY sales_count DESC "
	 		+ ")"
	 		+ "WHERE ROWNUM <= 5" , nativeQuery = true)
	 List<Object[]> findTop5BestSellingBooks();
	 
	 List<BookEntity> findByIdOrName(Integer id, String name);
	 
	 List<BookEntity> findByDateAddedBetween(Date startDate, Date endDate);
}
