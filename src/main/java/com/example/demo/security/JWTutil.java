package com.example.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTutil {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()+ expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido(String token)  {
		System.out.println(token);
		Claims claims= getClaims(token.substring(7));
		if(claims != null) {
			String username= claims.getSubject();
			Date expiracao= claims.getExpiration();
			Date now= new Date(System.currentTimeMillis());
			if(username != null && expiracao != null && now.before(expiracao)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername(String token)  {
		token= token.substring(7);
		Claims claims= getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
		}
	private Claims getClaims(String token)  {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}
}
