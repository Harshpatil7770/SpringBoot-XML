package com.xoriant.delivery.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;

	private Category category1;

	@BeforeEach
	public void setUp() {
		category1 = new Category();
		category1.setCategoryId(101);
		category1.setCategoryName("SmartPhones");
	}

	@Test
	public void addNewCategory() {
		when(categoryService.addNewCategory(category1)).thenReturn("addNewCategory() called");
		assertEquals("addNewCategory() called", categoryController.addNewCategory(category1));
	} 

}
