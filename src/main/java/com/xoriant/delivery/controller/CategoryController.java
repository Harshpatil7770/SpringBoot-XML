package com.xoriant.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save")
	private ResponseEntity<String> addNewCategory(@RequestBody Category category) {
		log.info("addNewCategory() called");
		String response = categoryService.addNewCategory(category);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
