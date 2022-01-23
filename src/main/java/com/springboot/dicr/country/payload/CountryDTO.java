package com.springboot.dicr.country.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.springboot.dicr.country.utils.BusinessMessages;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Country Model Information")
@Data
public class CountryDTO {

	@ApiModelProperty(value = "Country Id")
	private Long id;

	@ApiModelProperty(value = "Country Name")
	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_NAME_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String name;

	@ApiModelProperty(value = "Country Capital")
	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_CAPITAL_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String capital;

	@ApiModelProperty(value = "Country Region")
	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_REGION_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String region;

	@ApiModelProperty(value = "Country SubRegion")
	@NotEmpty
	@Size(min = 3, message = BusinessMessages.COUNTRY_SUBREGION_SHOULD_HAVE_AT_LEAST_THREE_CHARACTERS)
	private String subRegion;

	@ApiModelProperty(value = "Country Area")
	@NotEmpty
	private double area;

}
