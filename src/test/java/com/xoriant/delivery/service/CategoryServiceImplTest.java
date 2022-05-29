package com.xoriant.delivery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xoriant.delivery.dao.CategoryRepository;
import com.xoriant.delivery.exception.UserInputException;
import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.model.Category;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	private Category category1;

	@BeforeEach
	public void setUp() {
		category1 = new Category();
		category1.setCategoryId(101);
		category1.setCategoryName("SmartPhones");
	}

	@Test
	public void addNewBrand() {
		when(categoryRepository.save(category1)).thenReturn(category1);
		assertEquals("New Category Added Succesfully !", categoryServiceImpl.addNewCategory(category1));
	}

	@Test
	public void addNewBrandThrowsException_If_CategoryNameIsNull() {
		Category category = new Category();
		category.setCategoryId(201);
		category.setCategoryName(null);
		doThrow(UserInputException.class).when(categoryRepository).save(category);
		assertThrows(UserInputException.class, () -> {
			categoryRepository.save(category);
		});
	}

	@Test
	public void addNewBrandThrowsException_If_CategoryNameIsBlank() {
		Category category = new Category();
		category.setCategoryId(201);
		category.setCategoryName(" ");
		doThrow(UserInputException.class).when(categoryRepository).save(category);
		assertThrows(UserInputException.class, () -> {
			categoryRepository.save(category);
		});
	}
}
