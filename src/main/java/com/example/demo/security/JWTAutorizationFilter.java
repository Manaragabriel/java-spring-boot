package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAutorizationFilter extends BasicAuthenticationFilter{

	private JWTutil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAutorizationFilter(AuthenticationManager authenticationManager,JWTutil jwtUtil,UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil= jwtUtil;
		this.userDetailsService= userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse resp,
									FilterChain chain) throws IOException, ServletException {
		String header= req.getHeader("Authorization");
		System.out.println(header +"parte1");
		if(header != null && header.startsWith("Bearer ")) {
			System.out.println(header+"parte2");
			UsernamePasswordAuthenticationToken auth= getAuthentication(header);
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				chain.doFilter(req, resp);
			}
		}
	  
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.tokenValido(token)) {
			System.out.println(token);
			String username= jwtUtil.getUsername(token);
			UserDetails user= userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		}
		return null;
	}

}
