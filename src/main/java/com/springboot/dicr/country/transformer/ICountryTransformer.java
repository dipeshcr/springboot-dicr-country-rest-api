package com.springboot.dicr.country.transformer;

import com.springboot.dicr.country.model.CountryEntity;
import com.springboot.dicr.country.payload.CountryDTO;

public interface ICountryTransformer {

	CountryEntity transformFromDTO(final CountryDTO countryDTO);
	
	CountryDTO transformToDTO(final CountryEntity countryEntity);
	
}
