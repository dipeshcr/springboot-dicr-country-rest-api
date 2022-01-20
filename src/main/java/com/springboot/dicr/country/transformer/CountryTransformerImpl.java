package com.springboot.dicr.country.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.springboot.dicr.country.model.CountryEntity;
import com.springboot.dicr.country.payload.CountryDTO;

@Component
public class CountryTransformerImpl implements ICountryTransformer {

	private ModelMapper modelMapper;
	
	public CountryTransformerImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@Override
	public CountryEntity transformFromDTO(CountryDTO countryDTO) {
		CountryEntity country = modelMapper.map(countryDTO, CountryEntity.class);

		return country;
	}

	@Override
	public CountryDTO transformToDTO(CountryEntity countryEntity) {
		CountryDTO country = modelMapper.map(countryEntity, CountryDTO.class);

		return country;
	}

}
