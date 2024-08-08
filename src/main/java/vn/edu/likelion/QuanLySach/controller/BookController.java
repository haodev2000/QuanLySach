package vn.edu.likelion.QuanLySach.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.likelion.QuanLySach.dto.BestSellingBookDTO;
import vn.edu.likelion.QuanLySach.dto.BookDTO;
import vn.edu.likelion.QuanLySach.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	 @Autowired
	 private BookService bookService;

	 @GetMapping
	    public List<BookDTO> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    @GetMapping("/{id}")
	    public BookDTO getBookById(@PathVariable int id) {
	        return bookService.getBookById(id);
	    }

	    @PostMapping
	    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
	        return bookService.createBook(bookDTO);
	    }

	    @PutMapping("/{id}")
	    public BookDTO updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO) {
	        return bookService.updateBook(id, bookDTO);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteBook(@PathVariable int id) {
	        bookService.deleteBook(id);
	    }

	    @PostMapping("/{id}/sell")
	    public ResponseEntity<BookDTO> sellBook(@PathVariable int id) {
	        try {
	            BookDTO soldBook = bookService.sellBook(id);
	            return new ResponseEntity<>(soldBook, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @GetMapping("/sortedByPrice")
	    public ResponseEntity<List<BookDTO>> getAllBooksSortedByPrice() {
	        List<BookDTO> books = bookService.getAllBooksSortedByPrice();
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }
	    
	    @GetMapping("/sortedByQuantitySold")
	    public ResponseEntity<List<BookDTO>> getAllBooksSortedByQuantitySold() {
	        List<BookDTO> books = bookService.getAllBooksSortedByQuantitySold();
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }
	    
	    @GetMapping("/search")
	    public ResponseEntity<List<BookDTO>> searchBooksByIdOrName(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name) {
	        List<BookDTO> books = bookService.searchBooksByIdOrName(id, name);
	        return ResponseEntity.ok(books);
	    }
	    
	    @GetMapping("/searchByDate")
	    public List<BookDTO> searchBooksByDateRange(@RequestParam Date startDate, 
	                                                @RequestParam Date endDate) {
	        return bookService.findByDateAddedBetween(startDate, endDate);
	    }
	    
	    @GetMapping("/top5")
	    public List<BestSellingBookDTO> getTop5BestSellingBooks() {
	        return bookService.getTop5BestSellingBooks();
	    }
}
