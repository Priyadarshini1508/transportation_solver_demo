package com.example.transportation_demo; // Updated package name

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.transportation_demo") // Updated scan path
public class TransportationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationDemoApplication.class, args);
	}
}