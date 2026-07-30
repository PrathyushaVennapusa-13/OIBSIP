package com.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
		
		
	}
	@Bean
	ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
	@Bean
	HttpHeaders getHttpHeader()
	{
		return new HttpHeaders();
	}

}
