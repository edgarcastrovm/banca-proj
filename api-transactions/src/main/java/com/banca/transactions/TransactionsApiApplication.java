package com.banca.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.banca.utils")
public class TransactionsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApiApplication.class, args);
	}

}
