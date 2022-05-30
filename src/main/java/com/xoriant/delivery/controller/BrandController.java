package com.xoriant.delivery.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

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

	@GetMapping("/downloadCsv")
	public void downloadCsvFile(HttpServletResponse response) {
		try {
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment;filename=ecart.csv");
			ICsvBeanWriter csvwriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			String[] fileHeading = { "Brand Id", "Brand Name" };
			String[] pojoclassPropertynames = { "brandId", "brandName" };
			csvwriter.writeHeader(fileHeading);

			List<Brand> brandList = fetchAllBrands();
			if (null != brandList && !brandList.isEmpty()) {
				for (Brand brands : brandList) {
					csvwriter.write(brands, pojoclassPropertynames);
				}
			}
			csvwriter.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@GetMapping("/fetchAll-brands")
	public List<Brand> fetchAllBrands() {

		return brandService.fetchAllBrands();
	}

	@GetMapping("/downloadCsv/{brandName}")
	public void downloadCsvFile(HttpServletResponse response, @PathVariable String brandName) {
		try {
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment;filename=ecart.csv");
			ICsvBeanWriter csvwriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			String[] fileHeading = { "Brand Id", "Brand Name" };
			String[] pojoclassPropertynames = { "brandId", "brandName" };
			csvwriter.writeHeader(fileHeading);

			Brand existingBrand = findByBrandName(brandName);
			if (null != existingBrand) {
				csvwriter.write(existingBrand, pojoclassPropertynames);
			}
			csvwriter.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@GetMapping("/fetch/{brandName}")
	public Brand findByBrandName(@PathVariable String brandName) {
		return brandService.findByBrandName(brandName);

	}

	@GetMapping("/downloadCsv/brand/{brandId}")
	public void downloadCsvFile(HttpServletResponse response, @PathVariable int brandId) {
		try {
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment;filename=ecart.csv");
			ICsvBeanWriter csvwriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			String[] fileHeading = { "Brand Id", "Brand Name" };
			String[] pojoclassPropertynames = { "brandId", "brandName" };
			csvwriter.writeHeader(fileHeading);

			Optional<Brand> existingBrand = findByBrandId(brandId);
			if (existingBrand.isPresent()) {
				csvwriter.write(existingBrand, pojoclassPropertynames);
			}
			csvwriter.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@GetMapping("/find-brand/{brandId}")
	public Optional<Brand> findByBrandId(@PathVariable int brandId) {
		return brandService.findByBrandId(brandId);
	}
	
	
	
}
