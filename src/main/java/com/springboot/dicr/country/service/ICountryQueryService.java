package com.springboot.dicr.country.service;

import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.payload.CountryResponse;

public interface ICountryQueryService {

	CountryResponse findAllCountries(final Integer pageNo, final Integer pageSize, final String sortBy, final String sortDir);
	
	CountryDTO findCountryById(final Long id);

}
