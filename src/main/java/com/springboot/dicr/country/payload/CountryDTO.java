package com.springboot.dicr.country.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.springboot.dicr.country.utils.BusinessMessages;

import lombok.Data;

@Data
public class CountryDTO {

	private Long id;

	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_NAME_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String name;

	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_CAPITAL_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String capital;

	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_REGION_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String region;

	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_SUBREGION_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String subRegion;

	@NotEmpty
	private double area;

}
