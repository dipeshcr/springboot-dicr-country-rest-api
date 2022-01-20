package com.springboot.dicr.country.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.dicr.country.exception.ResourceNotFoundException;
import com.springboot.dicr.country.model.CountryEntity;
import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.payload.CountryResponse;
import com.springboot.dicr.country.repository.ICountryRepository;
import com.springboot.dicr.country.transformer.ICountryTransformer;

@Service
public class CountryQueryServiceImpl implements ICountryQueryService {

	private ICountryRepository countryRepository;

	private ICountryTransformer countryTransformer;

	public CountryQueryServiceImpl(ICountryRepository countryRepository, ICountryTransformer countryTransformer) {
		this.countryRepository = countryRepository;
		this.countryTransformer = countryTransformer;
	}

	@Override
	public CountryResponse findAllCountries(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<CountryEntity> allRegisteredCountriesByPageable = this.countryRepository.findAll(pageable);

		List<CountryEntity> allRegisteredCountries = allRegisteredCountriesByPageable.getContent();

		List<CountryDTO> content = allRegisteredCountries.stream()
				.map(countryEntity -> this.countryTransformer.transformToDTO(countryEntity))
				.collect(Collectors.toList());

		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setContent(content);
		countryResponse.setPageNo(pageNo);
		countryResponse.setPaseSize(pageSize);
		countryResponse.setTotalElements(allRegisteredCountriesByPageable.getTotalElements());
		countryResponse.setTotalPages(allRegisteredCountriesByPageable.getTotalPages());
		countryResponse.setLast(allRegisteredCountriesByPageable.isLast());

		return countryResponse;
	}

	@Override
	public CountryDTO findCountryById(Long id) {
		CountryEntity countryEntity = this.countryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

		return this.countryTransformer.transformToDTO(countryEntity);
	}

}
