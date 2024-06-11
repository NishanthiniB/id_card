package com.example.idcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
@ComponentScan("com.example.idcard.controller")
@ComponentScan("com.example.idcard.service.impl")
public class IdCardGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdCardGeneratorApplication.class, args);
	}

}
