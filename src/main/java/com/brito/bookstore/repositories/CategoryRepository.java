package com.brito.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brito.bookstore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
