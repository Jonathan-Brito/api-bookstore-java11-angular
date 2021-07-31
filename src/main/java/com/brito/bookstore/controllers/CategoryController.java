package com.brito.bookstore.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brito.bookstore.domain.Category;
import com.brito.bookstore.dtos.CategoryDTO;
import com.brito.bookstore.service.CategoryService;

@CrossOrigin("*")
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
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category){
		
		category = categoryService.create(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@Valid @PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){
		
		Category category = categoryService.update(id, categoryDTO);
		
		return ResponseEntity.ok().body(new CategoryDTO(category));
	}
	
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
