package com.github.gameserivcespring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
	info = @Info(
			title = "Game Service System API",
			description = "Game Service", version = "1.0.0",
			contact = @Contact(
					name = "Александр Марыгин",
					email = "aleksandermarigyn18@gmail.com"
			)
	)
)
@SpringBootApplication
public class GameServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameServiceSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
