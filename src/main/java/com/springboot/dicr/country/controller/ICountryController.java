package com.springboot.dicr.country.controller;

import org.springframework.http.ResponseEntity;

import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.payload.CountryResponse;

public interface ICountryController {

	ResponseEntity<CountryDTO> createCountry(final CountryDTO countryDTO);
	
	ResponseEntity<CountryDTO> getCountryById(final Long id);
	
	ResponseEntity<CountryDTO> updateCountry(final CountryDTO countryDTO, Long id);

	ResponseEntity<String> deleteCountryById(final Long id);

	ResponseEntity<CountryResponse> getAllCountries(final Integer pageNo, final Integer pageSize, final String sortBy, final String sortDir);

	
}
