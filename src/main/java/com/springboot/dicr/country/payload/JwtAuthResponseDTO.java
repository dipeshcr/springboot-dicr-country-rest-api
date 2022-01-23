package com.springboot.dicr.country.payload;

public class JwtAuthResponseDTO {

	private String acessToken;

	private String tokenType = "Bearer";

	public JwtAuthResponseDTO(String acessToken) {
		this.setAcessToken(acessToken);
	}

	public String getAcessToken() {
		return acessToken;
	}

	public void setAcessToken(String acessToken) {
		this.acessToken = acessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
