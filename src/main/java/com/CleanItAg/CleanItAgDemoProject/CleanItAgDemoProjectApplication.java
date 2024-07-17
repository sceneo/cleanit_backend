package com.CleanItAg.CleanItAgDemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CleanItAgDemoProjectApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/orders").allowedOrigins("http://localhost:8080","http://localhost:3000","http://localhost:5000");
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CleanItAgDemoProjectApplication.class, args);
	}

}
