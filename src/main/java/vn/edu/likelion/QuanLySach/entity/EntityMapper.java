package vn.edu.likelion.QuanLySach.entity;



import java.awt.print.Book;

import vn.edu.likelion.QuanLySach.dto.BookDTO;
import vn.edu.likelion.QuanLySach.dto.SalesDTO;
import vn.edu.likelion.QuanLySach.dto.UserDTO;

public class EntityMapper {
	
	public static BookDTO toBookDTO(BookEntity book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setDateAdded(book.getDateAdded());
        return bookDTO;
    }

    public static SalesDTO toSalesDTO(SalesEntity sale) {
        SalesDTO salesDTO = new SalesDTO(); 
        
        BookDTO bookDTO = new BookDTO();
        BookEntity b = sale.getBook();
        
        bookDTO.setId(b.getId());
        bookDTO.setName(b.getName());
        bookDTO.setPrice(b.getPrice());
        bookDTO.setQuantity(b.getQuantity());
        bookDTO.setDateAdded(b.getDateAdded());
        
        
        salesDTO.setId(sale.getId());
        salesDTO.setBook(bookDTO);
        salesDTO.setDateSale(sale.getDateSale());
        return salesDTO;
    }
    
    public static UserDTO toSalesDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();   
        userDTO.setId( user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
