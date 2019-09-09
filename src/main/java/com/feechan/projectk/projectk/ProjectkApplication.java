package com.feechan.projectk.projectk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProjectkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectkApplication.class, args);
	}
}
