package com.springboot.dicr.country.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.dicr.country.model.CountryEntity;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {

	Optional<CountryEntity> findByName(final String name);

}
