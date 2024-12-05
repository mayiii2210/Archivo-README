package com.appspc.apilibros.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	"com.appspc.apilibros.api",
	"com.appspc.apilibros.data.repositories", 
	"com.appspc.apilibros.data.interfaces.IRepository"})

	public class ApilibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApilibrosApplication.class, args);
	}
}