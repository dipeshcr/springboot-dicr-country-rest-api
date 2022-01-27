package com.springboot.dicr.country.service;

import org.springframework.stereotype.Service;

import com.springboot.dicr.country.exception.ResourceFoundException;
import com.springboot.dicr.country.exception.ResourceNotFoundException;
import com.springboot.dicr.country.model.CountryEntity;
import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.repository.ICountryRepository;
import com.springboot.dicr.country.transformer.ICountryTransformer;

@Service
public class CountryServiceImpl implements ICountryService {

	private ICountryRepository countryRepository;

	private ICountryTransformer countryTransformer;

	public CountryServiceImpl(ICountryRepository countryRepository, ICountryTransformer countryTransformer) {
		this.countryRepository = countryRepository;
		this.countryTransformer = countryTransformer;
	}

	@Override
	public CountryDTO createCountry(CountryDTO countryDTO) {
		CountryEntity country = this.countryTransformer.transformFromDTO(countryDTO);
		
		boolean countryExists = verifyIfCountryExists(country.getName());
		
		if(countryExists) {
			throw new ResourceFoundException("Country", "name", country.getName());
		}
		
		CountryEntity saveCountry = this.countryRepository.save(country);
		CountryDTO countryResponseDTO = this.countryTransformer.transformToDTO(saveCountry);

		return countryResponseDTO;
	}

	@Override
	public CountryDTO updateCountry(CountryDTO countryDTO, Long id) {

		CountryEntity countryEntity = this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

		countryEntity.setName(countryDTO.getName());
		countryEntity.setCapital(countryDTO.getCapital());
		countryEntity.setRegion(countryDTO.getRegion());
		countryEntity.setSubRegion(countryDTO.getSubRegion());
		countryEntity.setArea(countryDTO.getArea());

		CountryEntity saveCountry = this.countryRepository.save(countryEntity);

		return this.countryTransformer.transformToDTO(saveCountry);
	}

	@Override
	public void deleteCountryById(Long id) {
		this.countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

		this.countryRepository.deleteById(id);

	}

	private boolean verifyIfCountryExists(String countryName) {
		return this.countryRepository.findByName(countryName).isPresent();
	}

}
