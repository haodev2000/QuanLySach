package vn.edu.likelion.QuanLySach.service;

import java.util.Date;
import java.util.List;

import vn.edu.likelion.QuanLySach.dto.BestSellingBookDTO;
import vn.edu.likelion.QuanLySach.dto.BookDTO;

public interface BookService {
	
	List<BookDTO> getAllBooks();

	BookDTO getBookById(int id);

	BookDTO createBook(BookDTO bookDTO);

	BookDTO updateBook(int id, BookDTO bookDTO);

    void deleteBook(int id);
    
    BookDTO sellBook(int bookId);
    
    List<BookDTO> getAllBooksSortedByPrice();
    
    List<BookDTO> getAllBooksSortedByQuantitySold();
    
    List<BookDTO> searchBooksByIdOrName(Integer id, String name);

    List<BookDTO> findByDateAddedBetween(Date startDate, Date endDate);
    
    List<BestSellingBookDTO> getTop5BestSellingBooks();
}
