package com.springboot.dicr.country.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.dicr.country.model.CountryEntity;
import com.springboot.dicr.country.payload.CountryDTO;
import com.springboot.dicr.country.repository.ICountryRepository;

@SpringBootTest
class CountryServiceImplTest {

	@Autowired
	private ICountryRepository countryRepository;

	@Autowired
	private ICountryService countryService;

	@Test
	void testCreateCountry() {

		CountryDTO country = CountryDTO.builder().name("Peru").capital("Lima").region("Americas")
				.subRegion("Sout America").area(1285216).build();

		CountryDTO saveCountry = this.countryService.createCountry(country);

		assertNotNull(saveCountry.getId());

	}

	@Test
	void testUpdateCountry() {

		CountryDTO country = CountryDTO.builder().name("Angola").capital("Luanda").region("Africa")
				.subRegion("Middle Africa").area(1246700).build();

		CountryDTO saveCountry = this.countryService.createCountry(country);

		CountryDTO countryToUpdate = CountryDTO.builder().name("Angola").capital("Luanda").region("Africa")
				.subRegion("Middle Africa").area(1246700.9).build();

		CountryDTO updateCountry = this.countryService.updateCountry(countryToUpdate, saveCountry.getId());

		double expectedCountryArea = 1246700.9;

		assertEquals(expectedCountryArea, updateCountry.getArea());

	}

	@Test
	void testDeleteCountryById() {
		CountryDTO country = CountryDTO.builder().name("Angola").capital("Luanda").region("Africa")
				.subRegion("Middle Africa").area(1246700).build();

		CountryDTO saveCountry = this.countryService.createCountry(country);

		this.countryService.deleteCountryById(saveCountry.getId());

		Optional<CountryEntity> countryById = this.countryRepository.findById(saveCountry.getId());

		assertFalse(countryById.isPresent());

	}

}
