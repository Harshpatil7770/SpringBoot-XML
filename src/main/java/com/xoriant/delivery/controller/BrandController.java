package com.xoriant.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.service.BrandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/brands")
@Slf4j
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/save")
	public ResponseEntity<String> addNewBrand(@RequestBody Brand brand) {
		log.info("addNewBrand() called");
		String response = brandService.addNewBrand(brand);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
