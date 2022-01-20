package com.springboot.dicr.country.payload;


import lombok.Data;

@Data
public class CountryDTO {

	private Long id;
	
	private String name;

	private String capital;

	private String region;

	private String subRegion;

	private double area;
	
}
