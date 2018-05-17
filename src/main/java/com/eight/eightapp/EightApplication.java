package com.eight.eightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.eight.eightapp")
@SpringBootApplication
public class EightApplication {

	public static void main(String[] args) {
		SpringApplication.run(EightApplication.class, args);
	}
}
