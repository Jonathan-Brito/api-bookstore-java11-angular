package com.brito.bookstore.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brito.bookstore.domain.Book;
import com.brito.bookstore.dtos.BookDTO;
import com.brito.bookstore.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id){
		Book obj = bookService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category",
			defaultValue = "0") Integer id_cat){
		List<Book> list = bookService.findAll(id_cat);
		
		List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book obj){
		Book book = bookService.update(id, obj);
		
		return ResponseEntity.ok().body(book);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Book> updatePatch(@PathVariable Integer id, @RequestBody Book obj){
		Book book = bookService.update(id, obj);
		
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping
	public ResponseEntity<Book> create(@RequestParam(value = "category", 
			defaultValue = "0") Integer id_cat, @RequestBody Book obj){
		
		Book book = bookService.create(id_cat, obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}")
				.buildAndExpand(book.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		bookService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
