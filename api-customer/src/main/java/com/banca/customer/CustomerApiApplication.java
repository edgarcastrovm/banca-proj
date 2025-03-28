package com.banca.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.banca.utils")
public class CustomerApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

}
