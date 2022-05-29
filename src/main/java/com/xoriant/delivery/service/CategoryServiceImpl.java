package com.xoriant.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.delivery.dao.CategoryRepository;
import com.xoriant.delivery.exception.UserInputException;
import com.xoriant.delivery.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public String addNewCategory(Category category) {
		if (category.getCategoryName().isBlank() || category.getCategoryName().isBlank()) {
			throw new UserInputException();
		}
		categoryRepository.save(category);
		log.info("addNewCategory() called");
		String response = "New Category Added Succesfully !";

		return response;
	}
}
