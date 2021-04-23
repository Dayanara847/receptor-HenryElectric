package com.example.receptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceptorApplication.class, args);
	}

}
