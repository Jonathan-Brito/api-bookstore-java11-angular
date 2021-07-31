package com.brito.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brito.bookstore.domain.Book;
import com.brito.bookstore.domain.Category;
import com.brito.bookstore.repositories.BookRepository;
import com.brito.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Book findById(Integer id) {
		Optional<Book> obj = bookRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Book não encontrado! Id: " + id + ", Tipo: " + Book.class.getName()));
	}
	
	public List<Book> findAll(Integer id_cat){
		
		categoryService.findById(id_cat);
		
		return bookRepository.findAllByCategory(id_cat);
	}
	
	public Book update(Integer id, Book obj) {
		Book book = findById(id);
		
		updateData(book, obj); 
		
				
		return bookRepository.save(book);
	}
	
	private void updateData(Book book, Book obj) {
		book.setTitle(obj.getTitle());
		book.setAuthor(obj.getAuthor());
		book.setText(obj.getText());
	}
	
	public Book create(Integer id_cat, Book obj) {
		obj.setId(null);
		
		Category cat = categoryService.findById(id_cat);
		obj.setCategory(cat);
		
		return bookRepository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			bookRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException exception) {
			throw new com.brito.bookstore.service.exceptions.DataIntegrityViolationException
			("Book não pode ser deletado! Possui livros associados");
		}
	}

}
