package com.springboot.dicr.country.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.dicr.country.exception.CountryAPIException;
import com.springboot.dicr.country.utils.BusinessMessages;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		String userName = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setSubject(userName).setIssuedAt(currentDate).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;
	}

	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String token) {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new CountryAPIException(HttpStatus.BAD_REQUEST, BusinessMessages.INVALID_JWT_SIGNATURE);
		}
		catch (MalformedJwtException e) {
			throw new CountryAPIException(HttpStatus.BAD_REQUEST, BusinessMessages.INVALID_JWT_SIGNATURE);
		}
		catch (ExpiredJwtException e) {
			throw new CountryAPIException(HttpStatus.BAD_REQUEST, BusinessMessages.INVALID_JWT_SIGNATURE);
		}
		catch (UnsupportedJwtException e) {
			throw new CountryAPIException(HttpStatus.BAD_REQUEST, BusinessMessages.INVALID_JWT_SIGNATURE);
		}
		catch (IllegalArgumentException e) {
			throw new CountryAPIException(HttpStatus.BAD_REQUEST, BusinessMessages.INVALID_JWT_SIGNATURE);
		}
		
	}
}
