package com.springboot.dicr.country.service;

import com.springboot.dicr.country.payload.CountryDTO;

public interface ICountryService {
	
	CountryDTO createCountry(CountryDTO countryDTO);

	CountryDTO updateCountry(CountryDTO countryDTO, Long id);
	
	void deleteCountryById(Long id);

}
