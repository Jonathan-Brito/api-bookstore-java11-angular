package com.brito.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brito.bookstore.domain.Category;
import com.brito.bookstore.repositories.CategoryRepository;
import com.brito.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> optional = categoryRepository.findById(id);
		
		return optional.orElseThrow(() -> new  ObjectNotFoundException("Objeto não"
				+ "encontrado! " + id + Category.class.getName()));
	}

}
