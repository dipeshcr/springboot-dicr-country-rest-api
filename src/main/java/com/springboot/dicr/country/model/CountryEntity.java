package com.springboot.dicr.country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME", nullable = false)
	private String name;

	@Column(name="CAPITAL", nullable = false)
	private String capital;

	@Column(name="REGION", nullable = false)
	private String region;

	@Column(name="SUB_REGION", nullable = false)
	private String subRegion;

	@Column(name="AREA", nullable = false)
	private double area;
	
		

}
