package com.springboot.dicr.country.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.payload.CountryResponse;
import com.springboot.dicr.country.service.ICountryQueryService;
import com.springboot.dicr.country.service.ICountryService;
import com.springboot.dicr.country.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryControllerImpl implements ICountryController {

	private ICountryService countryService;

	private ICountryQueryService countryQueryService;

	public CountryControllerImpl(ICountryService countryService, ICountryQueryService countryQueryService) {
		this.countryService = countryService;
		this.countryQueryService = countryQueryService;
	}

	@Override
	@PostMapping
	public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
		return new ResponseEntity<>(this.countryService.createCountry(countryDTO), HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
		return new ResponseEntity<>(this.countryQueryService.findCountryById(id), HttpStatus.OK);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO, @PathVariable Long id) {
		return new ResponseEntity<>(this.countryService.updateCountry(countryDTO, id), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCountryById(@PathVariable Long id) {
		this.countryService.deleteCountryById(id);
		
		return new ResponseEntity<>("Country Entity With ID: " + id + " deleted sucessfully", HttpStatus.OK);
	}

	@Override
	@GetMapping
	public ResponseEntity<CountryResponse> getAllCountries(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return new ResponseEntity<>(this.countryQueryService.findAllCountries(pageNo, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}

}
