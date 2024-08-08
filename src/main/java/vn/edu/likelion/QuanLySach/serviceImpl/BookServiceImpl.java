package vn.edu.likelion.QuanLySach.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import vn.edu.likelion.QuanLySach.dto.BestSellingBookDTO;
import vn.edu.likelion.QuanLySach.dto.BookDTO;
import vn.edu.likelion.QuanLySach.entity.BookEntity;
import vn.edu.likelion.QuanLySach.repository.BookRepository;
import vn.edu.likelion.QuanLySach.repository.SaleRepository;
import vn.edu.likelion.QuanLySach.service.BookService;
import vn.edu.likelion.QuanLySach.service.SalesService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	SaleRepository saleRepository;
	
//	ModelMapper modelMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	 @Override
	    public List<BookDTO> getAllBooks() {
	        List<BookEntity> books = bookRepository.findAll();
	        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }

	    @Override
	    public BookDTO getBookById(int id) {
	        Optional<BookEntity> book = bookRepository.findById(id);
	        return book.map(this::convertToDTO).orElse(null);
	    }

	    @Override
	    public BookDTO createBook(BookDTO bookDTO) {
	        BookEntity book = new BookEntity();
	        book.setName(bookDTO.getName());
	        book.setQuantity(bookDTO.getQuantity());
	        book.setPrice(bookDTO.getPrice());
	        book.setDateAdded(bookDTO.getDateAdded());
	        BookEntity savedBook = bookRepository.save(book);
	        return convertToDTO(savedBook);
	    }

	    @Override
	    public BookDTO updateBook(int id, BookDTO bookDTO) {
	        Optional<BookEntity> bookOptional = bookRepository.findById(id);
	        if (bookOptional.isPresent()) {
	        	BookEntity book = bookOptional.get();
	            book.setName(bookDTO.getName());
	            book.setQuantity(bookDTO.getQuantity());
	            book.setPrice(bookDTO.getPrice());
	            book.setDateAdded(bookDTO.getDateAdded());
	            BookEntity updatedBook = bookRepository.save(book);
	            return convertToDTO(updatedBook);
	        }
	        return null;
	    }

	    @Override
	    public void deleteBook(int id) {
	        bookRepository.deleteById(id);
	    }

	    @Override
	    public BookDTO sellBook(int bookId) {
	        BookEntity book = bookRepository.findById(bookId)
	                .orElseThrow(() -> new RuntimeException("Book not found"));
	        if (book.getQuantity() > 0) {
	            book.setQuantity(book.getQuantity() - 1);
	            book = bookRepository.save(book);

	            // Create sale transaction
	            salesService.createSale(bookId);

	            return convertToDTO(book);
	        } else {
	            throw new RuntimeException("Book is out of stock");
	        }
	    }

		@Override
		public List<BookDTO> getAllBooksSortedByPrice() {
			 List<BookEntity> books = bookRepository.findAllByOrderByPriceAsc();
		     return books.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		
		@Override
	    public List<BookDTO> getAllBooksSortedByQuantitySold() {
	        List<BookEntity> books = bookRepository.findAll();
	        books.sort((b1, b2) -> {
	            int quantitySoldB1 = saleRepository.countByBook(b1);
	            int quantitySoldB2 = saleRepository.countByBook(b2);
	            return Integer.compare(quantitySoldB2, quantitySoldB1);
	        });
	        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }

		@Override
		public List<BookDTO> searchBooksByIdOrName(Integer id, String name) {
			 List<BookEntity> books = bookRepository.findByIdOrName(id, name);
		     return books.stream().map(this::convertToDTO).collect(Collectors.toList());
		}
		
		@Override
		public List<BookDTO> findByDateAddedBetween(Date startDate, Date endDate) {
			 String sql = "SELECT * FROM Book WHERE date_added BETWEEN ? AND ?";
			 List<BookEntity> books = jdbcTemplate.query(sql, new Object[]{startDate, endDate}, new BookRowMapper());
			 
			 return books.stream().map(this::convertToDTO).collect(Collectors.toList());
		}


		private static final class BookRowMapper implements RowMapper<BookEntity> {
	        @Override
	        public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
	            BookEntity book = new BookEntity();
	            book.setId(rs.getInt("id"));
	            book.setName(rs.getString("name"));
	            book.setQuantity(rs.getInt("quantity"));
	            book.setPrice(rs.getFloat("price"));
	            book.setDateAdded(rs.getDate("date_added"));
	            return book;
	        }
	    }

		 private BookDTO convertToDTO(BookEntity book) {
		        BookDTO bookDTO = new BookDTO();
		        bookDTO.setId(book.getId());
		        bookDTO.setName(book.getName());
		        bookDTO.setQuantity(book.getQuantity());
		        bookDTO.setPrice(book.getPrice());
		        bookDTO.setDateAdded(book.getDateAdded());
		        return bookDTO;
		    }
		 
		 @Override
		 public List<BestSellingBookDTO> getTop5BestSellingBooks() {
		     List<Object[]> results = bookRepository.findTop5BestSellingBooks();
		     return results.stream()
		             .map(result -> new BestSellingBookDTO(
		                     ((Number) result[0]).intValue(),
		                     (String) result[1],
		                     ((Number) result[2]).longValue()
		             ))
		             .collect(Collectors.toList());
		 }


		 
		 
		
}
