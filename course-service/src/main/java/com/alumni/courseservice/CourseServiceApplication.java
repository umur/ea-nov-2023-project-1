package com.alumni.courseservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Maharishi assignment - Alumni project final Nov 2023",
				description = "RESTful Microservices APIs Documentation",
				version = "v1.0.0",
				contact = @Contact(
						name = "Geoffrey Duncan Opiyo",
						email = "dunkygeoffrey39@gmail.com",
						url = "https://www.miu.edu/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.miu.edu/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Enterprise Architecture final project app development",
				url = "https://github.com/MoaidAlrazhy/ea-nov-2023-project-1"
		)
)
@SpringBootApplication
public class CourseServiceApplication {
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	@Bean // To make ModelMapper a Spring Bean.
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}
