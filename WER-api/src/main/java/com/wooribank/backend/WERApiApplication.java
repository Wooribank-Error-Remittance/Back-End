package com.wooribank.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WERApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WERApiApplication.class, args);
	}

}
