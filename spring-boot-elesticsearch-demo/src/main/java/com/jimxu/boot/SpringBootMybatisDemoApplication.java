package com.jimxu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisDemoApplication.class, args);
	}
}
