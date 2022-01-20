package com.springboot.dicr.country.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
	
	private List<CountryDTO> content;
	
	private Integer pageNo;
	
	private Integer paseSize;
	
	private Long totalElements;
	
	private Integer totalPages;
	
	private boolean last;
}
