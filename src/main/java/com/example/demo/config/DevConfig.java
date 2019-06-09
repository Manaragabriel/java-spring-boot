package com.example.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		service.instantiateTestDatabase();
		return true;
	}

	
}
