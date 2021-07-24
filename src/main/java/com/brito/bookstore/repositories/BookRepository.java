package com.brito.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.bookstore.domain.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
