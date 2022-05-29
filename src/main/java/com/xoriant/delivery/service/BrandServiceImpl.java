package com.xoriant.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.delivery.dao.BrandRepository;
import com.xoriant.delivery.exception.UserInputException;
import com.xoriant.delivery.model.Brand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public String addNewBrand(Brand brand) {
		if (brand.getBrandName().isEmpty() || brand.getBrandName().isBlank()) {
			throw new UserInputException();
		}
		log.info("addNewBrand() called");
		brandRepository.save(brand);
		String response = "New Brand Added Succesfully !";
		return response;
	}

}
