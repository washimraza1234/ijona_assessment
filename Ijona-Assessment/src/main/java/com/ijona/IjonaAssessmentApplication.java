package com.ijona;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IjonaAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IjonaAssessmentApplication.class, args);
	}

	// Used to register to the spring container
	// i.e., we can use the Autowired where ever we want in the program;
	// we will return ModelMapper object here
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
