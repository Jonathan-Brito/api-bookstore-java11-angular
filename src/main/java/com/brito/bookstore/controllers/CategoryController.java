package com.brito.bookstore.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brito.bookstore.domain.Category;
import com.brito.bookstore.dtos.CategoryDTO;
import com.brito.bookstore.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<Category> list = categoryService.findAll();
		
		List<CategoryDTO> listDTO = list.stream()
				.map(category -> new CategoryDTO(category))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id){
		
		Category optional = categoryService.findById(id);
		return ResponseEntity.ok().body(optional);
	}

}
