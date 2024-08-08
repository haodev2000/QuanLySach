package vn.edu.likelion.QuanLySach.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.likelion.QuanLySach.dto.SalesDTO;
import vn.edu.likelion.QuanLySach.entity.BookEntity;
import vn.edu.likelion.QuanLySach.entity.EntityMapper;
import vn.edu.likelion.QuanLySach.entity.SalesEntity;
import vn.edu.likelion.QuanLySach.repository.BookRepository;
import vn.edu.likelion.QuanLySach.repository.SaleRepository;
import vn.edu.likelion.QuanLySach.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	SaleRepository salesRepository;
	
	@Autowired
	private BookRepository bookRepository;

	 @Override
	 public List<SalesDTO> getAllSales() {
	        return salesRepository.findAll().stream()
	                .map(sale -> EntityMapper.toSalesDTO(sale))
	                .collect(Collectors.toList());
	    }

	  @Override
	    public SalesDTO getSaleById(int id) {
	        SalesEntity sale = salesRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Sale not found"));
	        return EntityMapper.toSalesDTO(sale);
	    }

	    @Override
	    public SalesDTO createSale(int bookId) {
	        BookEntity book = bookRepository.findById(bookId)
	                .orElseThrow(() -> new RuntimeException("Book not found"));
	        SalesEntity sale = new SalesEntity();
	        sale.setBook(book);
	        sale.setDateSale(new Date());
	        sale = salesRepository.save(sale);
	        return EntityMapper.toSalesDTO(sale);
	    }


	    @Override
	    public void deleteSale(int id) {
	        salesRepository.deleteById(id);
	    }
	
}
