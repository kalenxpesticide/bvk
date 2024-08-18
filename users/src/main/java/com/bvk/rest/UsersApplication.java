package com.bvk.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		System.out.println("Starting User Service .......");
		SpringApplication.run(UsersApplication.class, args);
	}

}
