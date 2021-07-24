package com.brito.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brito.bookstore.domain.Book;
import com.brito.bookstore.domain.Category;
import com.brito.bookstore.repositories.BookRepository;
import com.brito.bookstore.repositories.CategoryRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookRepository bookRepository;

	public void instanciaBaseDeDados() {
		Category cat1 = new Category(null, "Informática", "Livros de TI");
		Category cat2 = new Category(null, "Orientação a objetos", "Livros de TI");
		Category cat3 = new Category(null, "Programação", "Livros de TI");

		Book book1 = new Book(null, "Clean code", "Jonathan Brito", "Lorem ipsum", cat1);
		Book book2 = new Book(null, "Engenharia de software", "Jonathan Brito", "Lorem ipsum", cat1);
		Book book3 = new Book(null, "Orientação a objetos", "Jonathan Brito", "Lorem ipsum", cat2);
		Book book4 = new Book(null, "Programção", "Jonathan Brito", "Lorem ipsum", cat2);
		Book book5 = new Book(null, "Gestão de TI", "Jonathan Brito", "Lorem ipsum", cat3);
		Book book6 = new Book(null, "Sistemas computacionais", "Jonathan Brito", "Lorem ipsum", cat3);

		cat1.getBooks().addAll(Arrays.asList(book1, book2));
		cat2.getBooks().addAll(Arrays.asList(book2, book3, book4, book5));
		cat3.getBooks().addAll(Arrays.asList(book3, book4, book5, book6));
		

		this.categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		this.bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6));

	}

}
