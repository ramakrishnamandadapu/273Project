package com.cmpe273.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={ "com.cmpe273.controller","com.cmpe273.dao"})
public class SpringConfig {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class);
	}
}
