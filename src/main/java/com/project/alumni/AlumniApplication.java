package com.project.alumni;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlumniApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumniApplication.class, args);
	}

	@Bean // must be inside a @Configuration class
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
