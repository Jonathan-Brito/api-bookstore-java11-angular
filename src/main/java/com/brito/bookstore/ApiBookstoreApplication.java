package com.brito.bookstore;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brito.bookstore.domain.Book;
import com.brito.bookstore.domain.Category;
import com.brito.bookstore.repositories.BookRepository;
import com.brito.bookstore.repositories.CategoryRepository;

@SpringBootApplication
public class ApiBookstoreApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiBookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática", "Livros de TI");
		
		Book book1 = new Book(null, "Clean code", "Jonathan Brito", "Lorem ipsum", cat1);
		
		cat1.getBooks().addAll(Arrays.asList(book1));
		
		this.categoryRepository.saveAll(Arrays.asList(cat1));
		
		this.bookRepository.saveAll(Arrays.asList(book1));
		
	}

}
