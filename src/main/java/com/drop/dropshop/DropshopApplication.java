package com.drop.dropshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DropshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DropshopApplication.class, args);
	}

}
